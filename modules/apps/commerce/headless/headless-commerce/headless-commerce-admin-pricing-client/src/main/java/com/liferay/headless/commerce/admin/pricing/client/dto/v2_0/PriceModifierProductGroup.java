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

package com.liferay.headless.commerce.admin.pricing.client.dto.v2_0;

import com.liferay.headless.commerce.admin.pricing.client.function.UnsafeSupplier;
import com.liferay.headless.commerce.admin.pricing.client.serdes.v2_0.PriceModifierProductGroupSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class PriceModifierProductGroup implements Cloneable {

	public static PriceModifierProductGroup toDTO(String json) {
		return PriceModifierProductGroupSerDes.toDTO(json);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public String getPriceModifierExternalReferenceCode() {
		return priceModifierExternalReferenceCode;
	}

	public void setPriceModifierExternalReferenceCode(
		String priceModifierExternalReferenceCode) {

		this.priceModifierExternalReferenceCode =
			priceModifierExternalReferenceCode;
	}

	public void setPriceModifierExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			priceModifierExternalReferenceCodeUnsafeSupplier) {

		try {
			priceModifierExternalReferenceCode =
				priceModifierExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String priceModifierExternalReferenceCode;

	public Long getPriceModifierId() {
		return priceModifierId;
	}

	public void setPriceModifierId(Long priceModifierId) {
		this.priceModifierId = priceModifierId;
	}

	public void setPriceModifierId(
		UnsafeSupplier<Long, Exception> priceModifierIdUnsafeSupplier) {

		try {
			priceModifierId = priceModifierIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long priceModifierId;

	public String getProductGroupExternalReferenceCode() {
		return productGroupExternalReferenceCode;
	}

	public void setProductGroupExternalReferenceCode(
		String productGroupExternalReferenceCode) {

		this.productGroupExternalReferenceCode =
			productGroupExternalReferenceCode;
	}

	public void setProductGroupExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			productGroupExternalReferenceCodeUnsafeSupplier) {

		try {
			productGroupExternalReferenceCode =
				productGroupExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productGroupExternalReferenceCode;

	public Long getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(Long productGroupId) {
		this.productGroupId = productGroupId;
	}

	public void setProductGroupId(
		UnsafeSupplier<Long, Exception> productGroupIdUnsafeSupplier) {

		try {
			productGroupId = productGroupIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long productGroupId;

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public void setProductGroupName(
		UnsafeSupplier<String, Exception> productGroupNameUnsafeSupplier) {

		try {
			productGroupName = productGroupNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productGroupName;

	@Override
	public PriceModifierProductGroup clone() throws CloneNotSupportedException {
		return (PriceModifierProductGroup)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PriceModifierProductGroup)) {
			return false;
		}

		PriceModifierProductGroup priceModifierProductGroup =
			(PriceModifierProductGroup)object;

		return Objects.equals(toString(), priceModifierProductGroup.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return PriceModifierProductGroupSerDes.toJSON(this);
	}

}