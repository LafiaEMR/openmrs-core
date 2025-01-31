/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.web.filter.multitenancy;

import org.openmrs.api.db.hibernate.multitenancy.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TenantFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TenantFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		log.info("Inside DOFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// First check the request header for the tenant ID
		String tenantId = req.getHeader("X-TenantID");

		// If not found in header, check the cookies
		if (tenantId == null || tenantId.trim().isEmpty()) {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("X-TenantID".equals(cookie.getName())) {
						tenantId = cookie.getValue();
						break;
					}
				}
			}
		}

		// If still not found, set to "public"
		if (tenantId == null || tenantId.trim().isEmpty()) {
			log.error("No tenant ID provided in the request or cookies");
//			tenantId = "public";

			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Tenant ID is required");
			return;
		}

		TenantContext.setCurrentTenant(tenantId);
		try {
			chain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
    }
}
