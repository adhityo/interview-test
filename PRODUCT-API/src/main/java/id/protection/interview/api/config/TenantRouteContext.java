package id.protection.interview.api.config;

public class TenantRouteContext {

	private static final ThreadLocal<String> ACTIVE_TENANT = new ThreadLocal<>();
	
	public static String getActiveTenant() {
		return ACTIVE_TENANT.get();
	}
	public static void setActiveTenant (String tenant) {
		ACTIVE_TENANT.set(tenant);
	}
}
