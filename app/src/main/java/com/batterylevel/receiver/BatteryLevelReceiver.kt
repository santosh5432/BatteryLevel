package com.batterylevel.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast

class BatteryLevelReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_CHANGED) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100)
            val batteryPercent = (level * 100f) / scale.toFloat()
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING)

            if (batteryPercent <= 20f && !isCharging) {
                Toast.makeText(context, "Battery low! Please charge your device.", Toast.LENGTH_SHORT).show()
                context.unregisterReceiver(this)
            }
        }
    }
}