package com.xtaolabs.fuck_huaya.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.xtaolabs.fuck_huaya.hook.apps.HuayaHooker


@InjectYukiHookWithXposed(isUsingResourcesHook = false)
class HookEntry : IYukiHookXposedInit {

    override fun onHook() = encase {
        loadApp("cn.fzhuayantcoltd.huayaapp") {
            loadHooker(HuayaHooker)
        }
    }
}
