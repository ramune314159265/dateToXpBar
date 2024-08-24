package ramune314159265.dateToXpBar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class DateToXpBar extends JavaPlugin {
	static public JavaPlugin main;
	public ScheduledExecutorService scheduler;

	static public void setXp(Player player) {
		player.sendExperienceChange(
				YearInfo.getYearProgress(),
				YearInfo.getMonthDay()
		);
	}

	@Override
	public void onEnable() {
		DateToXpBar.main = this;
		getServer().getPluginManager().registerEvents(new PluginListener(), this);

		this.scheduler = Executors.newScheduledThreadPool(1);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nextSecond = now.plusSeconds(1).withNano(0);
		Duration duration = Duration.between(now, nextSecond);
		Bukkit.getLogger().info(String.valueOf(duration.toMillis()));

		this.scheduler.scheduleAtFixedRate(() -> {
			for (Player player : getServer().getOnlinePlayers()) {
				setXp(player);
			}
		}, duration.toMillis(), 1000, TimeUnit.MILLISECONDS);
	}

	@Override
	public void onDisable() {
		this.scheduler.shutdown();
	}
}
