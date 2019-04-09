package com.base.base.utilcode.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    private static final byte[] a = new byte[]{-128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final Md5Util b = new Md5Util();
    private static final char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private long[] d = new long[4];
    private long[] e = new long[2];
    private byte[] f = new byte[64];
    private String g;
    private byte[] h = new byte[16];

    private Md5Util() {
        this.a();
    }

    public static Md5Util getInstance() {
        return b;
    }

    public static long b2iu(byte var0) {
        return var0 < 0 ? (long) (var0 & 255) : (long) var0;
    }

    public static String byteHEX(byte var0) {
        char[] var1 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] var2;
        (var2 = new char[2])[0] = var1[var0 >>> 4 & 15];
        var2[1] = var1[var0 & 15];
        return new String(var2);
    }

    public static String toUltrashort(String var0) {
        return b.md5_8(var0);
    }

    public static String toShort(String var0) {
        return b.md5_16(var0);
    }

    public static String toLong(String var0) {
        return b.md5_32(var0);
    }

    public static String bytesToHexString(byte[] var0) {
        StringBuilder var1 = new StringBuilder(var0.length * 2);

        for (int var2 = 0; var2 < var0.length; ++var2) {
            var1.append(c[(var0[var2] & 240) >>> 4]);
            var1.append(c[var0[var2] & 15]);
        }

        return var1.toString();
    }

    public String md5_32_system(String var1) {
        MessageDigest var2 = null;

        try {
            var2 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        }

        var2.update(var1.getBytes(Charset.forName("UTF-8")));
        return bytesToHexString(var2.digest());
    }

    public String md5_32(String var1) {
        this.a();
        this.a(var1.getBytes(Charset.forName("UTF-8")), var1.length());
        byte[] var2;
        a(var2 = new byte[8], this.e, 8);
        int var3 = (var3 = (int) (this.e[0] >>> 3) & 63) < 56 ? 56 - var3 : 120 - var3;
        this.a(a, var3);
        this.a(var2, 8);
        a(this.h, this.d, 16);
        this.g = "";

        for (int var4 = 0; var4 < 16; ++var4) {
            this.g = this.g + byteHEX(this.h[var4]);
        }

        return this.g;
    }

    public String md5_16(String var1) {
        return this.md5_32(var1).substring(8, 24);
    }

    public String md5_8(String var1) {
        return this.md5_32(var1).substring(0, 8);
    }

    private void a() {
        this.e[0] = 0L;
        this.e[1] = 0L;
        this.d[0] = 1732584193L;
        this.d[1] = 4023233417L;
        this.d[2] = 2562383102L;
        this.d[3] = 271733878L;
    }

    private static long a(long var0, long var2, long var4, long var6, long var8, long var10, long var12) {
        return (long) ((int) (var0 += (var2 & var4 | ~var2 & var6) + var8 + var12) << (int) var10 | (int) var0 >>> (int) (32L - var10)) + var2;
    }

    private static long b(long var0, long var2, long var4, long var6, long var8, long var10, long var12) {
        return (long) ((int) (var0 += (var2 & var6 | var4 & ~var6) + var8 + var12) << (int) var10 | (int) var0 >>> (int) (32L - var10)) + var2;
    }

    private static long c(long var0, long var2, long var4, long var6, long var8, long var10, long var12) {
        return (long) ((int) (var0 += (var2 ^ var4 ^ var6) + var8 + var12) << (int) var10 | (int) var0 >>> (int) (32L - var10)) + var2;
    }

    private static long d(long var0, long var2, long var4, long var6, long var8, long var10, long var12) {
        return (long) ((int) (var0 += (var4 ^ (var2 | ~var6)) + var8 + var12) << (int) var10 | (int) var0 >>> (int) (32L - var10)) + var2;
    }

    private void a(byte[] var1, int var2) {
        byte[] var5 = new byte[64];
        int var4 = (int) (this.e[0] >>> 3) & 63;
        if ((this.e[0] += (long) (var2 << 3)) < (long) (var2 << 3)) {
            ++this.e[1];
        }

        this.e[1] += (long) (var2 >>> 29);
        int var3 = 64 - var4;
        if (var2 >= var3) {
            a(this.f, var1, var4, 0, var3);
            this.a(this.f);

            while (var3 + 63 < var2) {
                a(var5, var1, 0, var3, 64);
                this.a(var5);
                var3 += 64;
            }

            var4 = 0;
        } else {
            var3 = 0;
        }

        a(this.f, var1, var4, var3, var2 - var3);
    }

    private static void a(byte[] var0, byte[] var1, int var2, int var3, int var4) {
        for (int var5 = 0; var5 < var4; ++var5) {
            var0[var2 + var5] = var1[var3 + var5];
        }

    }

    private void a(byte[] var1) {
        long var2 = this.d[0];
        long var4 = this.d[1];
        long var6 = this.d[2];
        long var8 = this.d[3];
        long[] var10;
        long[] var10000 = var10 = new long[16];
        byte[] var11 = var1;
        long[] var14 = var10000;
        int var12 = 0;

        for (int var13 = 0; var13 < 64; var13 += 4) {
            var14[var12] = b2iu(var11[var13]) | b2iu(var11[var13 + 1]) << 8 | b2iu(var11[var13 + 2]) << 16 | b2iu(var11[var13 + 3]) << 24;
            ++var12;
        }

        var2 = a(var2, var4, var6, var8, var10[0], 7L, 3614090360L);
        var8 = a(var8, var2, var4, var6, var10[1], 12L, 3905402710L);
        var6 = a(var6, var8, var2, var4, var10[2], 17L, 606105819L);
        var4 = a(var4, var6, var8, var2, var10[3], 22L, 3250441966L);
        var2 = a(var2, var4, var6, var8, var10[4], 7L, 4118548399L);
        var8 = a(var8, var2, var4, var6, var10[5], 12L, 1200080426L);
        var6 = a(var6, var8, var2, var4, var10[6], 17L, 2821735955L);
        var4 = a(var4, var6, var8, var2, var10[7], 22L, 4249261313L);
        var2 = a(var2, var4, var6, var8, var10[8], 7L, 1770035416L);
        var8 = a(var8, var2, var4, var6, var10[9], 12L, 2336552879L);
        var6 = a(var6, var8, var2, var4, var10[10], 17L, 4294925233L);
        var4 = a(var4, var6, var8, var2, var10[11], 22L, 2304563134L);
        var2 = a(var2, var4, var6, var8, var10[12], 7L, 1804603682L);
        var8 = a(var8, var2, var4, var6, var10[13], 12L, 4254626195L);
        var6 = a(var6, var8, var2, var4, var10[14], 17L, 2792965006L);
        var4 = a(var4, var6, var8, var2, var10[15], 22L, 1236535329L);
        var2 = b(var2, var4, var6, var8, var10[1], 5L, 4129170786L);
        var8 = b(var8, var2, var4, var6, var10[6], 9L, 3225465664L);
        var6 = b(var6, var8, var2, var4, var10[11], 14L, 643717713L);
        var4 = b(var4, var6, var8, var2, var10[0], 20L, 3921069994L);
        var2 = b(var2, var4, var6, var8, var10[5], 5L, 3593408605L);
        var8 = b(var8, var2, var4, var6, var10[10], 9L, 38016083L);
        var6 = b(var6, var8, var2, var4, var10[15], 14L, 3634488961L);
        var4 = b(var4, var6, var8, var2, var10[4], 20L, 3889429448L);
        var2 = b(var2, var4, var6, var8, var10[9], 5L, 568446438L);
        var8 = b(var8, var2, var4, var6, var10[14], 9L, 3275163606L);
        var6 = b(var6, var8, var2, var4, var10[3], 14L, 4107603335L);
        var4 = b(var4, var6, var8, var2, var10[8], 20L, 1163531501L);
        var2 = b(var2, var4, var6, var8, var10[13], 5L, 2850285829L);
        var8 = b(var8, var2, var4, var6, var10[2], 9L, 4243563512L);
        var6 = b(var6, var8, var2, var4, var10[7], 14L, 1735328473L);
        var4 = b(var4, var6, var8, var2, var10[12], 20L, 2368359562L);
        var2 = c(var2, var4, var6, var8, var10[5], 4L, 4294588738L);
        var8 = c(var8, var2, var4, var6, var10[8], 11L, 2272392833L);
        var6 = c(var6, var8, var2, var4, var10[11], 16L, 1839030562L);
        var4 = c(var4, var6, var8, var2, var10[14], 23L, 4259657740L);
        var2 = c(var2, var4, var6, var8, var10[1], 4L, 2763975236L);
        var8 = c(var8, var2, var4, var6, var10[4], 11L, 1272893353L);
        var6 = c(var6, var8, var2, var4, var10[7], 16L, 4139469664L);
        var4 = c(var4, var6, var8, var2, var10[10], 23L, 3200236656L);
        var2 = c(var2, var4, var6, var8, var10[13], 4L, 681279174L);
        var8 = c(var8, var2, var4, var6, var10[0], 11L, 3936430074L);
        var6 = c(var6, var8, var2, var4, var10[3], 16L, 3572445317L);
        var4 = c(var4, var6, var8, var2, var10[6], 23L, 76029189L);
        var2 = c(var2, var4, var6, var8, var10[9], 4L, 3654602809L);
        var8 = c(var8, var2, var4, var6, var10[12], 11L, 3873151461L);
        var6 = c(var6, var8, var2, var4, var10[15], 16L, 530742520L);
        var4 = c(var4, var6, var8, var2, var10[2], 23L, 3299628645L);
        var2 = d(var2, var4, var6, var8, var10[0], 6L, 4096336452L);
        var8 = d(var8, var2, var4, var6, var10[7], 10L, 1126891415L);
        var6 = d(var6, var8, var2, var4, var10[14], 15L, 2878612391L);
        var4 = d(var4, var6, var8, var2, var10[5], 21L, 4237533241L);
        var2 = d(var2, var4, var6, var8, var10[12], 6L, 1700485571L);
        var8 = d(var8, var2, var4, var6, var10[3], 10L, 2399980690L);
        var6 = d(var6, var8, var2, var4, var10[10], 15L, 4293915773L);
        var4 = d(var4, var6, var8, var2, var10[1], 21L, 2240044497L);
        var2 = d(var2, var4, var6, var8, var10[8], 6L, 1873313359L);
        var8 = d(var8, var2, var4, var6, var10[15], 10L, 4264355552L);
        var6 = d(var6, var8, var2, var4, var10[6], 15L, 2734768916L);
        var4 = d(var4, var6, var8, var2, var10[13], 21L, 1309151649L);
        var2 = d(var2, var4, var6, var8, var10[4], 6L, 4149444226L);
        var8 = d(var8, var2, var4, var6, var10[11], 10L, 3174756917L);
        var6 = d(var6, var8, var2, var4, var10[2], 15L, 718787259L);
        var4 = d(var4, var6, var8, var2, var10[9], 21L, 3951481745L);
        this.d[0] += var2;
        this.d[1] += var4;
        this.d[2] += var6;
        this.d[3] += var8;
    }

    private static void a(byte[] var0, long[] var1, int var2) {
        int var3 = 0;

        for (int var4 = 0; var4 < var2; var4 += 4) {
            var0[var4] = (byte) ((int) (var1[var3] & 255L));
            var0[var4 + 1] = (byte) ((int) (var1[var3] >>> 8 & 255L));
            var0[var4 + 2] = (byte) ((int) (var1[var3] >>> 16 & 255L));
            var0[var4 + 3] = (byte) ((int) (var1[var3] >>> 24 & 255L));
            ++var3;
        }

    }
}
