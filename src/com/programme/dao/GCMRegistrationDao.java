package com.programme.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface GCMRegistrationDao {

	void sendTokenToServer(String token) throws ClientProtocolException, IOException;
}
