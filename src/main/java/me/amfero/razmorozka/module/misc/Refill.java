package me.amfero.razmorozka.module.misc;

import java.util.ArrayList;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import me.amfero.razmorozka.util.Timer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Refill extends Module {

	private final Setting delay = new Setting("Delay", this, 0, 0, 10);
	private final Setting gapStack = new Setting("GapStack", this, 50, 1, 64);
	private final Setting xpStack = new Setting("XPStack", this, 50, 1, 64);
	private final Setting crystalStack = new Setting("CrystalStack", this, 50, 1, 64);
	private final Timer timer = new Timer();
    private final ArrayList<Item> Hotbar = new ArrayList();
	
	public Refill(String name, String description, Category category) {
		super(name, description, category);
		addSetting(delay);
		addSetting(gapStack);
		addSetting(xpStack);
		addSetting(crystalStack);
		
	}

	public void onEnable() {
		
		if(mc.world == null) {
			return;
		}
		
        this.Hotbar.clear();
        for (int l_I = 0; l_I < 9; ++l_I) {
            ItemStack l_Stack = mc.player.inventory.getStackInSlot(l_I);
            if (!l_Stack.isEmpty() && !this.Hotbar.contains(l_Stack.getItem())) {
                this.Hotbar.add(l_Stack.getItem());
                continue;
            }
            this.Hotbar.add(Items.AIR);
        }
    }
	
	@SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
		if(mc.world == null) {
			return;
		}
		
		if (mc.currentScreen != null) {
            return;
        }
        if (!this.timer.passedMs(this.delay.getIntegerValue() * 1000)) {
            return;
        }
        for (int l_I = 0; l_I < 9; ++l_I) {
            if (!this.RefillSlotIfNeed(l_I)) continue;
            this.timer.reset();
            return;
        }
	}
	
    private boolean RefillSlotIfNeed(int p_Slot) {
        ItemStack l_Stack = mc.player.inventory.getStackInSlot(p_Slot);
        if (l_Stack.isEmpty() || l_Stack.getItem() == Items.AIR) {
            return false;
        }
        if (!l_Stack.isStackable()) {
            return false;
        }
        if (l_Stack.getCount() >= l_Stack.getMaxStackSize()) {
            return false;
        }
        if (l_Stack.getItem().equals(Items.GOLDEN_APPLE) && l_Stack.getCount() >= gapStack.getIntegerValue()) {
            return false;
        }
        if (l_Stack.getItem().equals(Items.EXPERIENCE_BOTTLE) && l_Stack.getCount() > xpStack.getIntegerValue()) {
            return false;
        }
        if (l_Stack.getItem().equals(Items.END_CRYSTAL) && l_Stack.getCount() >= crystalStack.getIntegerValue()) {
            return false;
        }
        for (int l_I = 9; l_I < 36; ++l_I) {
            ItemStack l_Item = mc.player.inventory.getStackInSlot(l_I);
            if (l_Item.isEmpty() || !this.CanItemBeMergedWith(l_Stack, l_Item)) continue;
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, l_I, 0, ClickType.QUICK_MOVE, mc.player);
            mc.playerController.updateController();
            return true;
        }
        return false;
    }

    private boolean CanItemBeMergedWith(ItemStack p_Source, ItemStack p_Target) {
        return p_Source.getItem() == p_Target.getItem() && p_Source.getDisplayName().equals(p_Target.getDisplayName());
    }
	
}
