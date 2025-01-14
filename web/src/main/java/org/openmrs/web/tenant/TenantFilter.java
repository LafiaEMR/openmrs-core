/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.web.tenant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.openmrs.api.impl.PatientServiceImpl;
import org.openmrs.tenant.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class TenantFilter extends OncePerRequestFilter {
	private static final Logger log = LoggerFactory.getLogger(TenantFilter.class);

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//		log.info("Inside doFilter method..");
//        
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String tenantId = httpRequest.getHeader("X-Tenant-ID");
//
//		log.info("tenantId:: {}", tenantId);
//
//        try {
//            if (tenantId != null) {
//                TenantContext.setTenantId(tenantId);
//            }
//            chain.doFilter(request, response);
//        } finally {
//            TenantContext.clear();
//        }
//    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		log.info("Inside doFilterInternal method..");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String tenantId = httpRequest.getHeader("X-Tenant-ID");

		log.info("tenantId:: {}", tenantId);

		try {
			if (tenantId != null) {
				TenantContext.setTenantId(tenantId);
			}
			filterChain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
	}

	@Override
    public void destroy() {
        // Cleanup logic if needed
    }
} 
