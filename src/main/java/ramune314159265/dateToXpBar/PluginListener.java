package ramune314159265.dateToXpBar;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PluginListener implements Listener {
	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		event.getPlayer().sendExperienceChange(
				YearInfo.getYearProgress(),
				YearInfo.getMonthDay()
		);
	}
}
