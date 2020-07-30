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

package com.liferay.commerce.order.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableSchema;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaField;
import com.liferay.petra.string.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_ORDER_ITEMS,
	service = ClayDataSetDisplayView.class
)
public class CommerceOrderItemClayTableDataSetDisplayView
	extends ClayTableDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField imageField = clayTableSchemaBuilder.addField(
			"image", StringPool.BLANK);

		imageField.setContentRenderer("image");

		ClayTableSchemaField skuField = clayTableSchemaBuilder.addField(
			"sku", "sku");

		skuField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addField("name", "name");

		clayTableSchemaBuilder.addField("options", "options");

		clayTableSchemaBuilder.addField(
			"requestedDeliveryDate", "delivery-date");

		clayTableSchemaBuilder.addField("deliveryGroup", "delivery-group");

		ClayTableSchemaField priceField = clayTableSchemaBuilder.addField(
			"price", "price");

		priceField.setContentRenderer("commerceTableCellSubscription");

		clayTableSchemaBuilder.addField("discount", "discount");

		clayTableSchemaBuilder.addField("quantity", "quantity");

		clayTableSchemaBuilder.addField("total", "total");

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}