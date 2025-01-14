/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.tenant;

public class TenantContext {

    private static final ThreadLocal<String> tenantId = new ThreadLocal<>();

    // Set the tenantId for the current thread
    public static void setTenantId(String id) {
        tenantId.set(id);
    }

    // Get the tenantId for the current thread
    public static String getTenantId() {
        return tenantId.get();
    }

    // Clear the tenantId for the current thread
    public static void clear() {
        tenantId.remove();
    }
}
