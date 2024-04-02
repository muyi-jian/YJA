import { defineConfig, loadEnv } from "vite";
import vue from "@vitejs/plugin-vue";
import eslintPlugin from "vite-plugin-eslint";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";

import Icons from "unplugin-icons/vite";
import IconsResolver from "unplugin-icons/resolver";
import { createSvgIconsPlugin } from "vite-plugin-svg-icons";

import path from "path";

const pathSrc = path.resolve(__dirname, "src");
import UnoCSS from "unocss/vite";

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  // 获取`.env`环境配置文件
  const env = loadEnv(mode, process.cwd());
  return {
    plugins: [
      vue(),
      UnoCSS({
        hmrTopLevelAwait: false
      }),
      createSvgIconsPlugin({
        // 指定需要缓存的图标文件夹
        iconDirs: [path.resolve(pathSrc, "assets/icons")],
        // 指定symbolId格式
        symbolId: "icon-[dir]-[name]"
        /**
         * 自定义插入位置 'body-last' | 'body-first'
         * @default: body-last
         */
        // inject: "body-last",

        /**
         * custom dom id
         * @default: __svg__icons__dom__
         */
        // customDomId: "__svg__icons__dom__"
      }),
      // eslintPlugin({ lintOnStart: true, cache: false }), // 项目运行时进行eslint检查
      eslintPlugin({
        include: ["src/**/*.js", "src/**/*.vue", "src/**/*.jsx", "src/**/*.ts", "src/**/*.tsx"],
        //  exclue: ['./node_modules/**'],
        cache: false
      }),
      AutoImport({
        // 自动导入 Vue 相关函数，如：ref, reactive, toRef 等
        imports: ["vue"],
        eslintrc: {
          enabled: true, // 是否自动生成 eslint 规则，建议生成之后设置 false
          filepath: "./.eslintrc-auto-import.json", // 指定自动导入函数 eslint 规则的文件
          globalsPropValue: true
        },
        resolvers: [
          ElementPlusResolver(),
          // 自动导入图标组件
          IconsResolver({
            prefix: "Icon"
          })
        ],
        dts: path.resolve(pathSrc, "types", "auto-imports.d.ts") // 指定自动导入函数TS类型声明文件路径
      }),
      Components({
        resolvers: [
          ElementPlusResolver(),
          // 自动注册图标组件
          IconsResolver({
            enabledCollections: ["ep"]
          })
        ],
        dirs: ["src/layout", "src/components"], //指定组件目录
        dts: path.resolve(pathSrc, "types", "components.d.ts") // 指定自动导入组件TS类型声明文件路径
      }),
      Icons({
        autoInstall: true
      })
    ],
    // 反向代理解决跨域问题
    server: {
      // host: 'localhost', // 只能本地访问
      host: "0.0.0.0", // 局域网别人也可访问
      port: Number(env.VITE_APP_PORT),
      // 运行时自动打开浏览器
      // open: true,
      proxy: {
        [env.VITE_APP_BASE_API]: {
          target: env.VITE_APP_SERVICE_API,
          changeOrigin: true,
          rewrite: (path) => path.replace(new RegExp("^" + env.VITE_APP_BASE_API), "")
        }
      }
    },
    resolve: {
      alias: {
        "@": pathSrc //相对路径别名配置，使用@代替src
      }
    },
    css: {
      // CSS 预处理器
      preprocessorOptions: {
        //define global scss variable
        scss: {
          javascriptEnabled: true,
          additionalData: '@use "@/assets/styles/common/variables.scss" as *;'
        }
      }
    }
  };
});
