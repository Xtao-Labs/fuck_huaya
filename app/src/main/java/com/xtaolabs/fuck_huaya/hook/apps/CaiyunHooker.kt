package com.xtaolabs.fuck_huaya.hook.apps

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker

object CaiyunHooker : YukiBaseHooker() {

    private fun fuckVip() {
        "d.g.j.o1".hook {
            injectMember {
                method {
                    name = "h0"
                }
                // this.B =
                beforeHook {
                    args().first().set("svip")
                }
            }
            injectMember {
                method {
                    name = "J"
                }
                replaceTo("svip")
            }
            injectMember {
                method {
                    name = "f0"
                }
                // this.x =
                beforeHook {
                    args().first().set(true)
                }
            }
            injectMember {
                method {
                    name = "N"
                }
                replaceTo(true)
            }
            injectMember {
                method {
                    name = "c0"
                }
                // this.z =
                beforeHook {
                    args().first().set(4701859200L)
                }
            }
            injectMember {
                method {
                    name = "A"
                }
                replaceTo(4701859200L)
            }
            injectMember {
                method {
                    name = "O"
                }
                afterHook {
                    if (args().first().cast<String>().equals("svip")) {
                        result = true
                    }
                }
            }
        }
    }

    override fun onHook() {
        fuckVip()
    }
}
