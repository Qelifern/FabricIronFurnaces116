package ironfurnaces.tileentity;

import com.google.common.collect.Lists;
import ironfurnaces.items.*;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.*;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import team.reborn.energy.Energy;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class BlockIronFurnaceTileBase extends TileEntityInventory implements Tickable, RecipeUnlocker, RecipeInputProvider {
    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};

    private int timer;
    private int currentAugment = 0; // 0 == none 1 == Blasting 2 == Smoking 3 == Speed 4 == Fuel
    /**
     * The number of ticks that the furnace will keep burning
     */
    private int furnaceBurnTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime = this.getCookTime();
    private final Object2IntOpenHashMap<Identifier> recipesUsed;
    protected final PropertyDelegate propertyDelegate;

    protected RecipeType<? extends AbstractCookingRecipe> recipeType;

    public BlockIronFurnaceTileBase(BlockEntityType<?> tileentitytypeIn) {
        super(tileentitytypeIn, 4);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return BlockIronFurnaceTileBase.this.furnaceBurnTime;
                    case 1:
                        return BlockIronFurnaceTileBase.this.currentItemBurnTime;
                    case 2:
                        return BlockIronFurnaceTileBase.this.cookTime;
                    case 3:
                        return BlockIronFurnaceTileBase.this.totalCookTime;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        BlockIronFurnaceTileBase.this.furnaceBurnTime = value;
                        break;
                    case 1:
                        BlockIronFurnaceTileBase.this.currentItemBurnTime = value;
                        break;
                    case 2:
                        BlockIronFurnaceTileBase.this.cookTime = value;
                        break;
                    case 3:
                        BlockIronFurnaceTileBase.this.totalCookTime = value;
                }

            }

            public int size() {
                return 4;
            }
        };
        this.recipesUsed = new Object2IntOpenHashMap();
        this.recipeType = RecipeType.SMELTING;
    }

    protected int getCookTime() {
        ItemStack stack = this.getStack(3);
        if (!stack.isEmpty()) {
            if (stack.getItem() instanceof ItemAugmentSpeed || stack.getItem() instanceof ItemAugmentBlasting || stack.getItem() instanceof ItemAugmentSmoking) {
                return getCookTimeConfig() / 2;
            }
            if (stack.getItem() instanceof ItemAugmentFuel) {
                return (int) (getCookTimeConfig() * 1.25);
            }
        }
        return getCookTimeConfig();
    }

    protected int getCookTimeConfig() {
        return 200;
    }

    private int getAugment(ItemStack stack) {
        if (stack.getItem() instanceof ItemAugmentBlasting) {
            return 1;
        } else if (stack.getItem() instanceof ItemAugmentSmoking) {
            return 2;
        } else if (stack.getItem() instanceof ItemAugmentSpeed) {
            return 3;
        } else if (stack.getItem() instanceof ItemAugmentFuel) {
            return 4;
        }
        return 0;
    }

    @Override
    public void tick() {
        boolean flag1 = false;
        if (currentAugment != getAugment(this.getStack(3))) {
            this.currentAugment = getAugment(this.getStack(3));
            this.furnaceBurnTime = 0;
        }
        if (this.isBurning()) {
            --this.furnaceBurnTime;
        }

        if (!this.world.isClient) {
            timer++;
            if (this.totalCookTime != this.getCookTime()) {
                this.totalCookTime = this.getCookTime();
            }
            if (!this.getStack(3).isEmpty()) {
                if (this.getStack(3).getItem() instanceof ItemAugmentBlasting) {
                    if (this.recipeType != RecipeType.BLASTING) {
                        this.recipeType = RecipeType.BLASTING;
                    }
                } else if (this.getStack(3).getItem() instanceof ItemAugmentSmoking) {
                    if (this.recipeType != RecipeType.SMOKING) {
                        this.recipeType = RecipeType.SMOKING;
                    }
                }
            } else {
                if (this.recipeType != RecipeType.SMELTING) {
                    this.recipeType = RecipeType.SMELTING;
                }
            }
            ItemStack itemstack = this.inventory.get(1);
            if (this.isBurning() || !itemstack.isEmpty() && !this.inventory.get(0).isEmpty()) {
                Recipe<?> irecipe = (Recipe)this.world.getRecipeManager().getFirstMatch(this.recipeType, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    if (itemstack.getItem() instanceof ItemHeater) {
                        if (itemstack.hasTag()) {
                            int x = itemstack.getTag().getInt("X");
                            int y = itemstack.getTag().getInt("Y");
                            int z = itemstack.getTag().getInt("Z");
                            BlockEntity te = world.getBlockEntity(new BlockPos(x, y, z));
                            if (te instanceof BlockWirelessHeaterTile) {
                                double energy = Energy.of(te).getEnergy();
                                if (energy >= 500) {
                                    Energy.of(te).extract(500);
                                    int fuel = (getFuelTime(new ItemStack(Items.COAL)) / 8);
                                    if (!this.getStack(3).isEmpty() && this.getStack(3).getItem() instanceof ItemAugmentFuel) {
                                        this.furnaceBurnTime = (fuel * 2) * this.getCookTime() / 200;
                                    } else if (!this.getStack(3).isEmpty() && this.getStack(3).getItem() instanceof ItemAugmentSpeed) {
                                        this.furnaceBurnTime = (fuel / 2) * this.getCookTime() / 200;
                                    } else {
                                        this.furnaceBurnTime = fuel * this.getCookTime() / 200;
                                    }
                                    this.currentItemBurnTime = this.furnaceBurnTime;
                                }
                            }
                        }
                    } else {
                        if (!this.getStack(3).isEmpty() && this.getStack(3).getItem() instanceof ItemAugmentFuel) {
                            this.furnaceBurnTime = 2 * (getFuelTime(itemstack)) * this.getCookTime() / 200;
                        } else if (!this.getStack(3).isEmpty() && this.getStack(3).getItem() instanceof ItemAugmentSpeed) {
                            this.furnaceBurnTime = (getFuelTime(itemstack) / 2) * this.getCookTime() / 200;
                        } else {
                            this.furnaceBurnTime = getFuelTime(itemstack) * this.getCookTime() / 200;
                        }
                    }
                    this.currentItemBurnTime = this.furnaceBurnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (!(itemstack.getItem() instanceof ItemHeater)) {
                            if (!itemstack.isEmpty()) {
                                Item item = itemstack.getItem();
                                itemstack.decrement(1);
                                if (itemstack.isEmpty()) {
                                    Item item1 = item.getRecipeRemainder();
                                    this.inventory.set(1, item1 == null ? ItemStack.EMPTY : new ItemStack(item1));
                                }
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(irecipe)) {
                    ++this.cookTime;
                    if (this.cookTime >= this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime();
                        this.smeltItem(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }
            if (timer % 24 == 0) {
                BlockState state = world.getBlockState(pos);
                if (state.get(Properties.LIT) != this.furnaceBurnTime > 0) {
                    world.setBlockState(pos, state.with(Properties.LIT, this.furnaceBurnTime > 0), 3);
                }
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    private boolean canSmelt(Recipe<?> recipe) {
        if (!this.inventory.get(0).isEmpty() && recipe != null) {
            ItemStack itemstack = recipe.getOutput();
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.inventory.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.size() && itemstack1.getCount() < itemstack1.getMaxCount()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxCount(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    private void smeltItem(Recipe<?> recipe) {
        timer = 0;
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemstack = this.inventory.get(0);
            ItemStack itemstack1 = recipe.getOutput();
            ItemStack itemstack2 = this.inventory.get(2);
            if (itemstack2.isEmpty()) {
                this.inventory.set(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.increment(itemstack1.getCount());
            }

            if (!this.world.isClient) {
                this.setLastRecipe(recipe);
            }

            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.inventory.get(1).isEmpty() && this.inventory.get(1).getItem() == Items.BUCKET) {
                this.inventory.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.decrement(1);
        }
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        Inventories.fromTag(tag, this.inventory);
        this.furnaceBurnTime = tag.getInt("BurnTime");
        this.cookTime = tag.getInt("CookTime");
        this.totalCookTime = tag.getInt("CookTimeTotal");
        this.timer = 0;
        this.currentAugment = tag.getInt("Augment");
        this.currentItemBurnTime = getFuelTime(this.inventory.get(1));
        CompoundTag compoundTag = tag.getCompound("RecipesUsed");
        Iterator var4 = compoundTag.getKeys().iterator();

        while (var4.hasNext()) {
            String string = (String) var4.next();
            this.recipesUsed.put(new Identifier(string), compoundTag.getInt(string));
        }
        /**
         CompoundNBT energyTag = tag.getCompound("energy");
         energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
         **/

        super.fromTag(state, tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, this.inventory);
        tag.putInt("BurnTime", this.furnaceBurnTime);
        tag.putInt("CookTime", this.cookTime);
        tag.putInt("CookTimeTotal", this.totalCookTime);
        tag.putInt("Augment", this.currentAugment);
        CompoundTag compoundTag = new CompoundTag();
        this.recipesUsed.forEach((identifier, integer) -> {
            compoundTag.putInt(identifier.toString(), integer);
        });
        tag.put("RecipesUsed", compoundTag);
        /**
         energy.ifPresent(h -> {
         CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
         tag.put("energy", compound);
         });
         **/

        return super.toTag(tag);
    }


    protected static int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return (Integer) AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(item, 0);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getFuelTime(stack) > 0 || stack.getItem() instanceof ItemHeater;
    }


    @Override
    public int[] IgetSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
        }
    }

    @Override
    public boolean IcanExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean IisItemValidForSlot(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        } else {
            ItemStack itemstack = this.inventory.get(1);
            return FurnaceBlockEntity.canUseAsFuel(stack) || FurnaceFuelSlot.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }

    public void setLastRecipe(Recipe<?> recipe) {
        if (recipe != null) {
            Identifier identifier = recipe.getId();
            this.recipesUsed.addTo(identifier, 1);
        }

    }

    public Recipe<?> getLastRecipe() {
        return null;
    }

    public void unlockLastRecipe(PlayerEntity player) {
    }

    public void dropExperience(PlayerEntity player) {
        List<Recipe<?>> list = this.method_27354(player.world, player.getPos());
        player.unlockRecipes((Collection) list);
        this.recipesUsed.clear();
    }

    public List<Recipe<?>> method_27354(World world, Vec3d vec3d) {
        List<Recipe<?>> list = Lists.newArrayList();
        ObjectIterator var4 = this.recipesUsed.object2IntEntrySet().iterator();

        while (var4.hasNext()) {
            Object2IntMap.Entry<Identifier> entry = (Object2IntMap.Entry) var4.next();
            world.getRecipeManager().get((Identifier) entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                dropExperience(world, vec3d, entry.getIntValue(), ((AbstractCookingRecipe) recipe).getExperience());
            });
        }

        return list;
    }

    private static void dropExperience(World world, Vec3d vec3d, int i, float f) {
        int j = MathHelper.floor((float) i * f);
        float g = MathHelper.fractionalPart((float) i * f);
        if (g != 0.0F && Math.random() < (double) g) {
            ++j;
        }

        while (j > 0) {
            int k = ExperienceOrbEntity.roundToOrbSize(j);
            j -= k;
            world.spawnEntity(new ExperienceOrbEntity(world, vec3d.x, vec3d.y, vec3d.z, k));
        }

    }

    public void provideRecipeInputs(RecipeFinder finder) {
        Iterator var2 = this.inventory.iterator();

        while (var2.hasNext()) {
            ItemStack itemStack = (ItemStack) var2.next();
            finder.addItem(itemStack);
        }

    }
}
