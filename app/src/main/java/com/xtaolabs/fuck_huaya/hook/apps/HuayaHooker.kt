package com.xtaolabs.fuck_huaya.hook.apps

import android.content.Context
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.highcapable.yukihookapi.hook.type.java.UnitType

object HuayaHooker : YukiBaseHooker() {
    private fun fuckSplashAd() {
        findClass("cn.fzhuayantcoltd.huayaapp.dto.ConfigDto", appClassLoader).hook {
            injectMember {
                method {
                    name = "isNoSplash"
                }
                replaceToTrue()
            }
        }
    }

    private fun fuckReadAd() {
        findClass("cn.fzhuayantcoltd.huayaapp.ui.activity.longcartoon.LongBookDetailActivity", appClassLoader).hook {
            injectMember {
                method {
                    name = "a"
                    param("cn.fzhuayantcoltd.huayaapp.dto.event.WatchVideoEvent")
                    returnType = UnitType
                }
                replaceUnit { }
            }
        }
    }

    private fun fuckSignInAd() {
        findClass("cn.fzhuayantcoltd.huayaapp.a.a", appClassLoader).hook {
            injectMember {
                method {
                    name = "e"
                    paramCount = 0
                    returnType = BooleanType
                }
                replaceToTrue()
            }
        }
    }

    private fun fuckSignInRewardAd() {
        findClass("cn.fzhuayantcoltd.huayaapp.ui.activity.task.TasksCenterActivity", appClassLoader).hook {
            injectMember {
                method {
                    name = "j"
                    paramCount = 0
                }
                replaceUnit {
                    method {
                        name = "h"
                        paramCount = 0
                    }.get(instance).call()
                }
            }
        }
    }

    private fun fuckUpdate() {
        findClass("cn.fzhuayantcoltd.huayaapp.util.f", appClassLoader).hook {
            injectMember {
                method {
                    name = "b"
                    paramCount = 2
                }
                replaceUnit { }
            }
        }
    }

    private fun fuckTeen() {
        findClass("cn.fzhuayantcoltd.huayaapp.util.x", appClassLoader).hook {
            injectMember {
                method {
                    name = "o"
                }
                replaceToTrue()
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
                    // ??????????????????
                    fuckSplashAd()
                    // ??????????????????
                    fuckReadAd()
                    // ???????????????feedbannerAd ??????
                    fuckSignInAd()
                    // ??????????????????????????????
                    fuckSignInRewardAd()
                    // ??????????????????
                    fuckUpdate()
                    // ???????????????????????????
                    fuckTeen()
                }
            }
        }
    }
}
