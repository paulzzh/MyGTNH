package com.paulzzh.mygtnh;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import static com.paulzzh.mygtnh.Utils.GSON;
import static com.paulzzh.mygtnh.config.MyGTNHConfig.gt_cape_url;

public class ClientUtils {

    public static class CapeFetcher extends Thread {

        private final String username;

        public CapeFetcher(String username) {
            this.username = username;
            setName("MyGTNH CapeFetcher Thread");
            setDaemon(true);
            start();
        }

        @Override
        public void run() {
            try {
                String url_base = gt_cape_url.endsWith("/") ? gt_cape_url : gt_cape_url + "/";
                URL profile_url = new URL(url_base + username + ".json");
                URLConnection profile_connection = profile_url.openConnection();
                profile_connection.setConnectTimeout(10000);
                profile_connection.setReadTimeout(10000);
                InputStream profile_stream = profile_connection.getInputStream();
                CustomSkinAPIProfile profile = GSON.fromJson(new InputStreamReader(profile_stream), CustomSkinAPIProfile.class);
                String cape_hash = profile.cape;
                if (cape_hash == null || cape_hash.isEmpty()) {
                    cape_hash = profile.textures.get("cape");
                }
                if (cape_hash == null || cape_hash.isEmpty()) {
                    return;
                }
                String cape_url = url_base + "textures/" + cape_hash;
                File cache = Paths.get("capes_cache/" + cape_hash).toFile();
                Files.createDirectories(Paths.get("capes_cache/"));
                ResourceLocation rL = new ResourceLocation(String.format("mygtnh/capes/%s", cape_hash));
                ThreadDownloadImageData dL = new ThreadDownloadImageData(cache, cape_url, null, new IImageBuffer() {
                    @Override
                    public BufferedImage parseUserSkin(BufferedImage p_78432_1_) {
                        return p_78432_1_;
                    }

                    @Override
                    public void func_152634_a() {
                        MyGTNH.CAPE_CACHE.put(username, rL);
                    }
                });
                Minecraft.getMinecraft().getTextureManager().loadTexture(rL, dL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static class CustomSkinAPIProfile {
        public String username;
        public LinkedHashMap<String, String> textures;

        public String skin;
        public String cape;
        public String elytra;
    }
}
