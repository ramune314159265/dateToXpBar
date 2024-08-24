package ramune314159265.dateToXpBar;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class DateToXpBar extends JavaPlugin {
	public ScheduledExecutorService scheduler;

	static public void setXp(Player player) {
		player.sendExperienceChange(
				YearInfo.getYearProgress(),
				YearInfo.getMonthDay()
		);
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PluginListener(), this);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nextSecond = now.plusSeconds(1).withNano(0); // 次の秒の始まり
		Duration duration = Duration.between(now, nextSecond);

		this.scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(() -> {
			for (Player player : getServer().getOnlinePlayers()) {
				setXp(player);
			}
		}, duration.toMillis(), 1, TimeUnit.SECONDS);
	}

	@Override
	public void onDisable() {
		this.scheduler.shutdown();
	}
}
