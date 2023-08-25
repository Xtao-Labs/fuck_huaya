package com.xtaolabs.fuck_huaya.hook.apps

import android.content.Context
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.highcapable.yukihookapi.hook.type.java.UnitType

object HuayaHooker : YukiBaseHooker() {
    private fun fuckSplashAd() {
        // return openScreenDto != null && openScreenDto.isNoSplash();
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
        // bookReadLimitDialog.show();
        findClass(
            "cn.fzhuayantcoltd.huayaapp.ui.activity.longcartoon.LongBookDetailActivity",
            appClassLoader
        ).hook {
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
        // return this.m.user.isValidVip();
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
        // AdFromEnum.SIGNIN
        findClass(
            "cn.fzhuayantcoltd.huayaapp.ui.activity.task.TasksCenterActivity",
            appClassLoader
        ).hook {
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
        // bookUpdateDialog.show();
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
        // isTodayPreventionAddictionDialog1_
        findClass("cn.fzhuayantcoltd.huayaapp.util.y", appClassLoader).hook {
            injectMember {
                method {
                    name = "r"
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
                    // 去除开屏广告
                    fuckSplashAd()
                    // 去除阅读广告
                    fuckReadAd()
                    // 去除签到、feedbannerAd 广告
                    fuckSignInAd()
                    // 去除签到双倍奖励广告
                    fuckSignInRewardAd()
                    // 取消更新弹窗
                    fuckUpdate()
                    // 取消青少年模式弹窗
                    fuckTeen()
                }
            }
        }
    }
}
