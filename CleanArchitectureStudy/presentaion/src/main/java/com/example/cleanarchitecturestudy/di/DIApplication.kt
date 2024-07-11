package com.example.cleanarchitecturestudy.di

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.imqa.core.IMQAOption
import io.imqa.crash.IMQACrashAgent
import io.imqa.mpm.IMQAMpmAgent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Koin 을 사용하기 위한 Class
 * context 와 module 을 선언하여 주입시킨다.
 *
 * 해당 class 는 Manifest 에 선언하여 사용하기 때문에 별다른 호출부는 존재하지 않는다.
 */
@HiltAndroidApp
class DIApplication: Application() {
    @SuppressLint("Lint-LogDetector")
    override fun onCreate() {
        super.onCreate()

        val imqaOption = IMQAOption()
        imqaOption.buildType = false
        imqaOption.uploadPeriod = true
        imqaOption.crashDirectUploadFlag = true
        imqaOption.keepFileAtUploadFail = false

        imqaOption.httpTracing = true
        imqaOption.printLog = true
        imqaOption.behaviorTracing = true
        imqaOption.eventTracing = true
        imqaOption.dumpInterval = 5000
        imqaOption.fileInterval = 1
//
        //
//        imqaOption.serverUrl = "http://192.168.0.37:3000"
//        imqaOption.crashServerUrl = "http://192.168.0.37:3000"

        imqaOption.serverUrl = "https://collector.imqa.io"
        imqaOption.crashServerUrl = "https://collector.imqa.io"
        IMQAMpmAgent.getInstance()
            .setOption(imqaOption)
            .setContext(this, "")
            .setProjectKey("\$2b\$05\$RlPp1r0L4ElVcpn.lqEfl./hQ6BoUifHQf2Oe4qJxT/o6oGcbYY7C^#1U8389ATPE/szowZGlK27A==")
            .init()

        //IMQACrashAgent.sendCustomException(Exception("HI"))
//            .setInitListener { success ->
//                if (success) {
//                    // init() 메서드가 성공적으로 완료된 경우 처리할 로직
//                    IMQAMpmAgent.getInstance().startScreen("CUSTOMScreen")
//                    IMQACrashAgent.sendCustomException(Exception("HI"))
//                    Log.d("IMQAMpmAgent", "Initialization completed successfully")
//                } else {
//                    // init() 메서드가 실패한 경우 처리할 로직
//                    Log.d("IMQAMpmAgent", "Initialization failed")
//                }
//            }

        Log.d("SERVERRRRR", imqaOption.serverUrl)


       // IMQAMpmAgent.getInstance().startScreen("CUSTOMScreen")
        startKoin {
            androidContext(this@DIApplication)

            /*// vararg 로 넣어서 선언 가능.
            modules(
                apiModule,
                networkModule,
                rankingDataModule,
                repositoryModule,
                selectDataModule,
                viewModelModule
            )*/

            // 개별로 선언 가능
//            modules(apiModule)
//            modules(localDataModule)
//            modules(networkModule)
//            modules(remoteDataModule)
//            modules(repositoryModule)
//            modules(viewModelModule)
//            modules(useCaseModule)
        }
    }
}