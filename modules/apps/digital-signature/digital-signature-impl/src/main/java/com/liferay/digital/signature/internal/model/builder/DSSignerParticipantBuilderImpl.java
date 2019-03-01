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

package com.liferay.digital.signature.internal.model.builder;

import com.liferay.digital.signature.internal.model.DSSignerParticipantImpl;
import com.liferay.digital.signature.model.DSSignerParticipant;
import com.liferay.digital.signature.model.builder.DSSignerParticipantBuilder;

/**
 * @author Michael C. Han
 */
public class DSSignerParticipantBuilderImpl
	extends BaseSigningDSParticipantBuilder<DSSignerParticipant>
	implements DSSignerParticipantBuilder {

	public DSSignerParticipantBuilderImpl(
		String name, String email, int routingOrder) {

		super(name, email, routingOrder);
	}

	@Override
	protected DSSignerParticipantImpl createDSSignerParticipantImpl() {
		DSSignerParticipantImpl dsSignerParticipantImpl =
			new DSSignerParticipantImpl(
				getName(), getEmail(), getRoutingOrder());

		return dsSignerParticipantImpl;
	}

}