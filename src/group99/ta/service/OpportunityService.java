package group99.ta.service;

import group99.ta.model.Opportunity;
import group99.ta.storage.DataStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles posting and browsing opportunities.
 * Maps to backlog items:
 * - MO-01 Post Opportunity with Requirements
 * - MO-19 Configure Additional Screening Steps
 * - TA-05 Browse Opportunities
 * - TA-06 View Opportunity Type and Requirements
 */
public class OpportunityService {
    private final DataStore dataStore;

    public OpportunityService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void postOpportunity(Opportunity opportunity) {
        validateOpportunity(opportunity);

        List<Opportunity> all = dataStore.loadOpportunities();
        boolean updated = false;

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getOpportunityId().equals(opportunity.getOpportunityId())) {
                all.set(i, opportunity);
                updated = true;
                break;
            }
        }

        if (!updated) {
            all.add(opportunity);
        }

        dataStore.saveOpportunities(all);
    }

    public List<Opportunity> getAllOpportunities() {
        return dataStore.loadOpportunities();
    }

    public Opportunity findById(String opportunityId) {
        for (Opportunity opportunity : dataStore.loadOpportunities()) {
            if (opportunity.getOpportunityId().equals(opportunityId)) {
                return opportunity;
            }
        }
        return null;
    }

    /**
     * Simple keyword search for TA-side browsing.
     */
    public List<Opportunity> searchByKeyword(String keyword) {
        List<Opportunity> result = new ArrayList<>();
        String lower = keyword == null ? "" : keyword.toLowerCase();

        for (Opportunity opportunity : dataStore.loadOpportunities()) {
            if (opportunity.getModuleName().toLowerCase().contains(lower)
                    || opportunity.getModuleCode().toLowerCase().contains(lower)
                    || opportunity.getRequiredSkills().toLowerCase().contains(lower)
                    || opportunity.getRoleType().toLowerCase().contains(lower)) {
                result.add(opportunity);
            }
        }
        return result;
    }

    private void validateOpportunity(Opportunity opportunity) {
        if (isBlank(opportunity.getOpportunityId())) {
            throw new IllegalArgumentException("Opportunity ID is required.");
        }
        if (isBlank(opportunity.getModuleCode())) {
            throw new IllegalArgumentException("Module code is required.");
        }
        if (isBlank(opportunity.getModuleName())) {
            throw new IllegalArgumentException("Module name is required.");
        }
        if (isBlank(opportunity.getRoleType())) {
            throw new IllegalArgumentException("Role type is required.");
        }
        if (isBlank(opportunity.getDeadline())) {
            throw new IllegalArgumentException("Application deadline is required.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}