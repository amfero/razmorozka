package me.amfero.razmorozka.module.render;

import me.amfero.razmorozka.module.Category;
import me.amfero.razmorozka.module.Module;
import me.amfero.razmorozka.setting.Setting;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class ExtraTab extends Module {

	public final Setting tabSize = new Setting("TabSize", this, 250, 1, 600); 
	
	public ExtraTab(String name, String description, Category category) {
		super(name, description, category);
		addSetting(tabSize);
	}

	public static String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn) {
        String dname = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        return dname;
    }
	
}
