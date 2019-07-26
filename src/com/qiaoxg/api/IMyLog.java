package com.qiaoxg.api;

import com.qiaoxg.callback.LogCallback;

public interface IMyLog {

	void errorLog(String msg);

	void infoLog(String msg);

	void debugLog(String msg, LogCallback callback);

}
