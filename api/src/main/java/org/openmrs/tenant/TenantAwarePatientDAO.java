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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.api.db.hibernate.HibernatePatientDAO;
import org.springframework.stereotype.Repository;

//@Repository
//public class TenantAwarePatientDAO extends HibernatePatientDAO {
//
////    @Override
////    protected Criteria createCriteria(String alias) {
////        Criteria criteria = super.createCriteria(alias);
////        String currentTenantId = TenantContext.getTenantId();
////        if (currentTenantId != null) {
////            criteria.add(Restrictions.eq("tenantId", currentTenantId));
////        }
////        return criteria;
////    }
//} 
