package com.fox.shop.storage.types;

public enum FileFormat {
  PNG(".png"),
  JPEG(".jpeg"),
  JPG(".jpg");
  private final String format;

  FileFormat(final String format) {
    this.format = format;
  }

  public static FileFormat fromFormatValue(final String formatValue) {
    for (var it : values()) {
      if (it.getFormat().equals(formatValue))
        return it;
    }
    return FileFormat.JPEG;
  }

  public String getFormat() {
    return format;
  }
}
