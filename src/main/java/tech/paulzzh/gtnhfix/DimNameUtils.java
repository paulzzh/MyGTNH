package tech.paulzzh.gtnhfix;

public class DimNameUtils {
    public static String getDimName(int dim) {
        String name = "DIM" + dim;
        switch (dim) {
            case -1:
                name = "下界 (Ne)";
                break;
            case 0:
                name = "主世界 (OW)";
                break;
            case 1:
                name = "末地 (EN)";
                break;
            case 2:
                name = "通灵空间 (不建议使用)";
                break;
            case 7:
                name = "暮色森林 (TF)";
                break;
            case 25:
                name = "鸟神星 (MM)";
                break;
            case 28:
                name = "月球 (Mo)";
                break;
            case 29:
                name = "火星 (Ma)";
                break;
            case 30:
                name = "小行星带 (As)";
                break;
            case 31:
                name = "半人马Bb (CA)";
                break;
            case 32:
                name = "巴纳德C (BC)";
                break;
            case 33:
                name = "柯伊伯带 (KB)";
                break;
            case 34:
                name = "火星空间站 (MS)";
                break;
            case 35:
                name = "木卫二 (Eu)";
                break;
            case 36:
                name = "木卫一 (Io)";
                break;
            case 37:
                name = "水星 (Me)";
                break;
            case 38:
                name = "火卫一 (Ph)";
                break;
            case 39:
                name = "金星 (Ve)";
                break;
            case 40:
                name = "火卫二 (De)";
                break;
            case 41:
                name = "土卫二 (En)";
                break;
            case 42:
                name = "谷神星 (Ce)";
                break;
            case 43:
                name = "木卫三 (Ga)";
                break;
            case 44:
                name = "土卫六 (Ti)";
                break;
            case 45:
                name = "木卫四 (Ca)";
                break;
            case 46:
                name = "天卫四 (Ob)";
                break;
            case 47:
                name = "海卫八 (Pr)";
                break;
            case 48:
                name = "海卫一 (Tr)";
                break;
            case 49:
                name = "冥王星 (Pl)";
                break;
            case 80:
                name = "静态火星空间站 (SMS)";
                break;
            case 81:
                name = "巴纳德E (BE)";
                break;
            case 82:
                name = "巴纳德F (BF)";
                break;
            case 83:
                name = "妊神星 (Ha)";
                break;
            case 84:
                name = "织女B (VB)";
                break;
            case 85:
                name = "鲸鱼座T星E (TE)";
                break;
            case 86:
                name = "天卫五 (Mi)";
                break;
            case 87:
                name = "金星空间站 (VS)";
                break;
            case 88:
                name = "静态金星空间站 (SVS)";
                break;
            case 50:
                name = "外域 (Outer)";
                break;
            case 55:
                name = "精神世界 (巫术)";
                break;
            case 56:
                name = "痛苦迷宫 (巫术)";
                break;
            case 60:
                name = "基岩矿界 (Bedrock)";
                break;
            case 63:
                name = "罗斯128ba (RA)";
                break;
            case 64:
                name = "罗斯128b (RB)";
                break;
            case 69:
                name = "口袋世界 (神秘视界)";
                break;
            case 70:
                name = "镜中世界 (巫术)";
                break;
            case 100:
                name = "漆黑世界 (DD)";
                break;
            case 112:
                name = "深渊世界 (End Of Time)";
                break;
            case 173:
                name = "虚空漫步者世界 (魔像密经)";
                break;
            case 227:
                name = "废土世界 (GT++)";
                break;
            case 228:
                name = "澳大利亚 (GT++)";
                break;
            default:
                if(dim>2 && dim<7){
                    name = "空间站 ("+(dim-2)+"号)";
                    break;
                }
                if(dim>7 && dim<25){
                    name = "空间站 ("+(dim-3)+"号)";
                    break;
                }
                if(dim>178){
                    name = "花园/虚空世界 "+dim;
                    break;
                }
        }
        return name;
    }
}
