package ramune314159265.dateToXpBar;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class PluginListener implements Listener {
	@EventHandler()
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		BukkitScheduler scheduler = Bukkit.getScheduler();
		scheduler.runTaskLater(DateToXpBar.main, () -> {
			event.getPlayer().sendExperienceChange(
					YearInfo.getYearProgress(),
					YearInfo.getMonthDay()
			);
		}, 1L);
	}
}
