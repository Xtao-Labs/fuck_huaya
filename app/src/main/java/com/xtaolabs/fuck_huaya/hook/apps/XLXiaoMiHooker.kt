package com.xtaolabs.fuck_huaya.hook.apps

import android.os.Environment
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker

object XLXiaoMiHooker : YukiBaseHooker() {

    private val CacheFilePATH = Environment.getExternalStorageDirectory().absolutePath + "/Download/DLManager"

    private fun fuckXL() {
        "com.android.providers.downloads.XLDownloadApplication".hook {
            injectMember {
                method {
                    name = "generateCacheFileDir"
                }
                replaceTo(CacheFilePATH)
            }
        }
        "com.android.providers.downloads.config.XLConfig".hook {
            injectMember {
                method {
                    name = "setDebug"
                }
                replaceUnit {  }
            }
            injectMember {
                method {
                    name = "isDebug"
                }
                replaceToFalse()
            }
        }
    }

    override fun onHook() {
        fuckXL()
    }
}
