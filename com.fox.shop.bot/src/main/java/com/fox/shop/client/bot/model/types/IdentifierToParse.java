package com.fox.shop.client.bot.model.types;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum IdentifierToParse {
  NAME("name", "nam"),
  TIME("time"),
  PLACE_IDENTIFIER("place"),
  PEOPLE_AMOUNT("amount"),
  PHONE("phone", "smartphone");

  List<String> values;

  IdentifierToParse(String... values) {
    this.values = Arrays.asList(values);
  }

  public static Optional<String> extractDataByIdentifier(
      String data,
      IdentifierToParse identifier
  ) {
    if (!StringUtils.hasText(data) || identifier == null)
      return Optional.empty();
    data = data.toLowerCase();
    final List<String> identifierDataInLowerCase = identifier.getValues().stream().map(String::toLowerCase).collect(Collectors.toList());

    for (final String itValue : identifierDataInLowerCase) {
      if (data.contains(itValue))
        return Optional.of(data.split(itValue)[1]);
    }

    return Optional.empty();
  }

  public static boolean isContainsValue(String value) {
    value = value.toLowerCase();
    for (var itIdentifier : IdentifierToParse.values()) {
      final List<String> identifierDataInLowerCase = itIdentifier.getValues().stream().map(String::toLowerCase).collect(Collectors.toList());
      if (identifierDataInLowerCase.contains(value))
        return true;
    }
    return false;
  }

  public static Optional<IdentifierToParse> getIdentifierFromValue(final String value) {
    for (var itIdentifier : IdentifierToParse.values()) {
      if (itIdentifier.getValues().contains(value))
        return Optional.of(itIdentifier);
    }
    return Optional.empty();
  }

  public List<String> getValues() {
    return values;
  }
}
