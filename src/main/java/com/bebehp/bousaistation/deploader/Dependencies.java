package com.bebehp.bousaistation.deploader;

public class Dependencies {
	private final String remote;
	private final String local;

	public Dependencies(final String remote, final String local) {
		this.remote = remote;
		this.local = local;
	}

	public String getRemote() {
		return this.remote;
	}

	public String getLocal() {
		return this.local;
	}
}
