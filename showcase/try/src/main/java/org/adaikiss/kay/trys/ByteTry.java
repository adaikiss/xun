package org.adaikiss.kay.trys;

import org.apache.commons.lang.StringUtils;

public class ByteTry {
	public static String Int2IP(int ipCode) {
        byte a = (byte)((ipCode & 0xFF000000) >> 0x18);
        byte b = (byte)((ipCode & 0x00FF0000) >> 0xF);
        byte c = (byte)((ipCode & 0x0000FF00) >> 0x8);
        byte d = (byte)(ipCode & 0x000000FF);
        return new StringBuilder().append(a).append(".").append(b).append(".").append(c).append(".").append(d).toString();
    }

    public static int IP2Int(String ipStr) {
        String[] ip = StringUtils.split(ipStr, ".");
        int ipCode = 0xFFFFFF00 | Integer.parseInt(ip[3]);
        ipCode = ipCode & 0xFFFF00FF | (Integer.parseInt(ip[2]) << 0x8);
        ipCode = ipCode & 0xFF00FFFF | (Integer.parseInt(ip[1]) << 0xF);
        ipCode = ipCode & 0x00FFFFFF | (Integer.parseInt(ip[0]) << 0x18);
        return ipCode;
    }

	public static void main(String...args){
		int ipa = ByteTry.IP2Int("192.168.1.1");
		int ipb = ByteTry.IP2Int("192.168.2.255");
		int ipc = ByteTry.IP2Int("192.168.1.254");
		System.out.println(ipa);
		System.out.println(ByteTry.Int2IP(ipa));
		System.out.println(ipb);
		System.out.println(ByteTry.Int2IP(ipb));
		System.out.println(ipc);
		System.out.println(ByteTry.Int2IP(ipc));
		System.out.println(ipc>ipa);
		System.out.println(ipc<ipb);
//		String ip = "192.168.1.1";
//		String[] ips = ip.split("\\.");
//		System.out.println(ips.length);
//		byte buf[] = new byte[ips.length];
//		long value = 0;
//		for (int i = 0; i < ips.length; i++) {
//			buf[i] = (byte) Integer.parseInt(ips[i]);
//			value |= ((buf[i] & 0xff) << (i * 8));
//			System.out.printf("%X\n", value);
//		}
//		System.out.println(value);
	}
}
