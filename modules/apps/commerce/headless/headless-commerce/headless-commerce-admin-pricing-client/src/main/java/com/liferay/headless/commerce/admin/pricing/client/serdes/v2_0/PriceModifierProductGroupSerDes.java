/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.commerce.admin.pricing.client.serdes.v2_0;

import com.liferay.headless.commerce.admin.pricing.client.dto.v2_0.PriceModifierProductGroup;
import com.liferay.headless.commerce.admin.pricing.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class PriceModifierProductGroupSerDes {

	public static PriceModifierProductGroup toDTO(String json) {
		PriceModifierProductGroupJSONParser
			priceModifierProductGroupJSONParser =
				new PriceModifierProductGroupJSONParser();

		return priceModifierProductGroupJSONParser.parseToDTO(json);
	}

	public static PriceModifierProductGroup[] toDTOs(String json) {
		PriceModifierProductGroupJSONParser
			priceModifierProductGroupJSONParser =
				new PriceModifierProductGroupJSONParser();

		return priceModifierProductGroupJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		PriceModifierProductGroup priceModifierProductGroup) {

		if (priceModifierProductGroup == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (priceModifierProductGroup.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(priceModifierProductGroup.getId());
		}

		if (priceModifierProductGroup.getPriceModifierExternalReferenceCode() !=
				null) {

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceModifierExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(
					priceModifierProductGroup.
						getPriceModifierExternalReferenceCode()));

			sb.append("\"");
		}

		if (priceModifierProductGroup.getPriceModifierId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priceModifierId\": ");

			sb.append(priceModifierProductGroup.getPriceModifierId());
		}

		if (priceModifierProductGroup.getProductGroupExternalReferenceCode() !=
				null) {

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productGroupExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(
				_escape(
					priceModifierProductGroup.
						getProductGroupExternalReferenceCode()));

			sb.append("\"");
		}

		if (priceModifierProductGroup.getProductGroupId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productGroupId\": ");

			sb.append(priceModifierProductGroup.getProductGroupId());
		}

		if (priceModifierProductGroup.getProductGroupName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productGroupName\": ");

			sb.append("\"");

			sb.append(_escape(priceModifierProductGroup.getProductGroupName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PriceModifierProductGroupJSONParser
			priceModifierProductGroupJSONParser =
				new PriceModifierProductGroupJSONParser();

		return priceModifierProductGroupJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		PriceModifierProductGroup priceModifierProductGroup) {

		if (priceModifierProductGroup == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (priceModifierProductGroup.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(priceModifierProductGroup.getId()));
		}

		if (priceModifierProductGroup.getPriceModifierExternalReferenceCode() ==
				null) {

			map.put("priceModifierExternalReferenceCode", null);
		}
		else {
			map.put(
				"priceModifierExternalReferenceCode",
				String.valueOf(
					priceModifierProductGroup.
						getPriceModifierExternalReferenceCode()));
		}

		if (priceModifierProductGroup.getPriceModifierId() == null) {
			map.put("priceModifierId", null);
		}
		else {
			map.put(
				"priceModifierId",
				String.valueOf(priceModifierProductGroup.getPriceModifierId()));
		}

		if (priceModifierProductGroup.getProductGroupExternalReferenceCode() ==
				null) {

			map.put("productGroupExternalReferenceCode", null);
		}
		else {
			map.put(
				"productGroupExternalReferenceCode",
				String.valueOf(
					priceModifierProductGroup.
						getProductGroupExternalReferenceCode()));
		}

		if (priceModifierProductGroup.getProductGroupId() == null) {
			map.put("productGroupId", null);
		}
		else {
			map.put(
				"productGroupId",
				String.valueOf(priceModifierProductGroup.getProductGroupId()));
		}

		if (priceModifierProductGroup.getProductGroupName() == null) {
			map.put("productGroupName", null);
		}
		else {
			map.put(
				"productGroupName",
				String.valueOf(
					priceModifierProductGroup.getProductGroupName()));
		}

		return map;
	}

	public static class PriceModifierProductGroupJSONParser
		extends BaseJSONParser<PriceModifierProductGroup> {

		@Override
		protected PriceModifierProductGroup createDTO() {
			return new PriceModifierProductGroup();
		}

		@Override
		protected PriceModifierProductGroup[] createDTOArray(int size) {
			return new PriceModifierProductGroup[size];
		}

		@Override
		protected void setField(
			PriceModifierProductGroup priceModifierProductGroup,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					priceModifierProductGroup.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"priceModifierExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					priceModifierProductGroup.
						setPriceModifierExternalReferenceCode(
							(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priceModifierId")) {
				if (jsonParserFieldValue != null) {
					priceModifierProductGroup.setPriceModifierId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"productGroupExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					priceModifierProductGroup.
						setProductGroupExternalReferenceCode(
							(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productGroupId")) {
				if (jsonParserFieldValue != null) {
					priceModifierProductGroup.setProductGroupId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productGroupName")) {
				if (jsonParserFieldValue != null) {
					priceModifierProductGroup.setProductGroupName(
						(String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}