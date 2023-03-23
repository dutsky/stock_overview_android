package com.example.stockoverview

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

lateinit var channel: MethodChannel

class Application : android.app.Application() {
    private lateinit var flutterEngine : FlutterEngine

    override fun onCreate() {
        super.onCreate()

        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put("flutter_engine_id", flutterEngine)

        channel = MethodChannel(flutterEngine.dartExecutor, "stock_overview_channel_android_client")
    }
}
