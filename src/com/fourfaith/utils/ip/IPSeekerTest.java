package com.fourfaith.utils.ip;

import java.io.IOException;

@SuppressWarnings("unused")
public class IPSeekerTest {

    public static void main(String[] args) {
    	IPSeeker seeker = null;
		try {
			seeker = new IPSeeker(FileUtil.classpath("/qqwry.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		IPLocation location = seeker.getLocation((byte) 120, (byte) 42, (byte) 46, (byte) 98);
	}
    
}