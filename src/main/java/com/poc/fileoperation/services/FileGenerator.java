package com.poc.fileoperation.services;

import com.itextpdf.text.DocumentException;

import java.io.OutputStream;
import java.util.Map;

public interface FileGenerator {

  OutputStream writeToOutputStream(OutputStream outputStream, Map<String, String> data) throws DocumentException;
}
