package com.xtaolabs.fuck_huaya.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.xtaolabs.fuck_huaya.hook.apps.CaiyunHooker
import com.xtaolabs.fuck_huaya.hook.apps.CimocHooker
import com.xtaolabs.fuck_huaya.hook.apps.HuayaHooker
import com.xtaolabs.fuck_huaya.hook.apps.XLXiaoMiHooker


@InjectYukiHookWithXposed(isUsingResourcesHook = false)
class HookEntry : IYukiHookXposedInit {

    override fun onHook() = encase {
        loadApp("cn.fzhuayantcoltd.huayaapp") {
            loadHooker(HuayaHooker)
        }
        loadApp("com.cimoc.haleydu") {
            loadHooker(CimocHooker)
        }
        loadApp("com.nowcasting.activity") {
            loadHooker(CaiyunHooker)
        }
        loadApp("com.android.providers.downloads") {
            loadHooker(XLXiaoMiHooker)
        }
    }
}
