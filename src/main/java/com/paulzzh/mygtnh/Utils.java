package com.paulzzh.mygtnh;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
    public static String getDimName(int dim) {
        String name = "DIM" + dim;
        switch (dim) {
            case -1 -> name = "下界 (Ne)";
            case 0 -> name = "主世界 (OW)";
            case 1 -> name = "末地 (EN)";
            case 2 -> name = "通灵空间 (不建议使用)";
            case 7 -> name = "暮色森林 (TF)";
            case 25 -> name = "鸟神星 (MM)";
            case 28 -> name = "月球 (Mo)";
            case 29 -> name = "火星 (Ma)";
            case 30 -> name = "小行星带 (As)";
            case 31 -> name = "半人马Bb (CA)";
            case 32 -> name = "巴纳德C (BC)";
            case 33 -> name = "柯伊伯带 (KB)";
            case 34 -> name = "火星空间站 (MS)";
            case 35 -> name = "木卫二 (Eu)";
            case 36 -> name = "木卫一 (Io)";
            case 37 -> name = "水星 (Me)";
            case 38 -> name = "火卫一 (Ph)";
            case 39 -> name = "金星 (Ve)";
            case 40 -> name = "火卫二 (De)";
            case 41 -> name = "土卫二 (En)";
            case 42 -> name = "谷神星 (Ce)";
            case 43 -> name = "木卫三 (Ga)";
            case 44 -> name = "土卫六 (Ti)";
            case 45 -> name = "木卫四 (Ca)";
            case 46 -> name = "天卫四 (Ob)";
            case 47 -> name = "海卫八 (Pr)";
            case 48 -> name = "海卫一 (Tr)";
            case 49 -> name = "冥王星 (Pl)";
            case 80 -> name = "静态火星空间站 (SMS)";
            case 81 -> name = "巴纳德E (BE)";
            case 82 -> name = "巴纳德F (BF)";
            case 83 -> name = "妊神星 (Ha)";
            case 84 -> name = "织女B (VB)";
            case 85 -> name = "鲸鱼座T星E (TE)";
            case 86 -> name = "天卫五 (Mi)";
            case 87 -> name = "金星空间站 (VS)";
            case 88 -> name = "静态金星空间站 (SVS)";
            case 50 -> name = "外域 (Outer)";
            case 55 -> name = "精神世界 (巫术)";
            case 56 -> name = "痛苦迷宫 (巫术)";
            case 60 -> name = "基岩矿界 (Bedrock)";
            case 63 -> name = "罗斯128ba (RA)";
            case 64 -> name = "罗斯128b (RB)";
            case 69 -> name = "口袋世界 (神秘视界)";
            case 70 -> name = "镜中世界 (巫术)";
            case 100 -> name = "漆黑世界 (DD)";
            case 112 -> name = "深渊世界 (End Of Time)";
            case 173 -> name = "虚空漫步者世界 (魔像密经)";
            case 227 -> name = "废土世界 (GT++)";
            case 228 -> name = "澳大利亚 (GT++)";
            default -> {
                if (dim > 2 && dim < 7) {
                    name = "空间站 (" + (dim - 2) + "号)";
                    break;
                }
                if (dim > 7 && dim < 25) {
                    name = "空间站 (" + (dim - 3) + "号)";
                    break;
                }
                if (dim > 178) {
                    name = "花园/虚空世界 " + dim;
                }
            }
        }
        return name;
    }

    public static class ThreadUrlPusher extends Thread {

        private final String url;

        public ThreadUrlPusher(String url) {
            this.url = url;
            setName("MyGTNH Url callback Thread");
            setDaemon(true);
            start();
        }

        @Override
        public void run() {
            try {
                URL url = new URL(this.url);
                URLConnection connection = url.openConnection();
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
