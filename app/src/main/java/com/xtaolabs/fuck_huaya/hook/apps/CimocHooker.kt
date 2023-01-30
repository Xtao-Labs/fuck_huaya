package com.xtaolabs.fuck_huaya.hook.apps

import android.content.Context
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker

object CimocHooker : YukiBaseHooker() {

    private fun fuckAd() {
        findClass("com.haleydu.cimoc.manager.PreferenceManager", appClassLoader).hook {
            injectMember {
                method {
                    name = "getBoolean"
                    paramCount = 2
                }
                afterHook {
                    if (args().first().cast<String>().equals("pref_global_shutdown_ad")) {
                        result = true
                    }
                }
            }
        }
    }

    private fun fuckPermissionCheck() {
        findClass("com.haleydu.cimoc.ui.activity.MainActivity", appClassLoader).hook {
            injectMember {
                method {
                    name = "showPermission"
                }
                replaceUnit {  }
            }
        }
    }

    override fun onHook() {
        "com.stub.StubApp".hook {
            injectMember {
                method {
                    name = "attachBaseContext"
                }
                afterHook {
                    appClassLoader = args().first().cast<Context>()!!.classLoader
                    fuckAd()
                    fuckPermissionCheck()
                }
            }
        }
    }
}
