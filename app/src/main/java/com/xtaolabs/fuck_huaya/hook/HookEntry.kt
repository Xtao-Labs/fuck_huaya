package com.xtaolabs.fuck_huaya.hook

import android.content.Context
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.highcapable.yukihookapi.hook.type.java.UnitType
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit


@InjectYukiHookWithXposed(isUsingResourcesHook = false)
class HookEntry : IYukiHookXposedInit {

    override fun onHook() = encase {
        loadApp("cn.fzhuayantcoltd.huayaapp") {
            "com.stub.StubApp".hook {
                injectMember {
                    method {
                        name = "attachBaseContext"
                    }
                    afterHook {
                        appClassLoader = args().first().cast<Context>()!!.classLoader
                        // 去除开屏广告
                        findClass("cn.fzhuayantcoltd.huayaapp.dto.ConfigDto", appClassLoader).hook {
                            injectMember {
                                method {
                                    name = "isNoSplash"
                                }
                                replaceToTrue()
                            }
                        }
                        // 去除阅读广告
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
                        // 去除签到、feedbannerAd 广告
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
                        // 去除签到双倍奖励广告
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
                        // 取消更新弹窗
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
                }
            }
        }
    }
}