package group99.ta.service;

import group99.ta.model.ApplicationRecord;
import group99.ta.storage.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationService {

    private DataStore dataStore;

    public ApplicationService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void submitApplication(ApplicationRecord record) {

        if (isDuplicateApplication(record.getApplicantId(), record.getOpportunityId())) {
            System.out.println("Application already exists!");
            return;
        }

        List<ApplicationRecord> list = dataStore.loadApplications();
        list.add(record);
        dataStore.saveApplications(list);
    }
    public boolean isDuplicateApplication(String applicantId, String opportunityId) {
        List<ApplicationRecord> list = dataStore.loadApplications();

        for (ApplicationRecord record : list) {
            if (record.getApplicantId().equals(applicantId)
                    && record.getOpportunityId().equals(opportunityId)) {
                return true;
            }
        }

        return false;
    }

    public List<ApplicationRecord> getApplicationsByOpportunity(String opportunityId) {
        return dataStore.loadApplications().stream()
                .filter(a -> a.getOpportunityId().equals(opportunityId))
                .collect(Collectors.toList());
    }

    public List<ApplicationRecord> getApplicationsByApplicant(String applicantId) {
        return dataStore.loadApplications().stream()
                .filter(a -> a.getApplicantId().equals(applicantId))
                .collect(Collectors.toList());
    }

    public ApplicationRecord findByApplicationId(String applicationId) {
        for (ApplicationRecord record : dataStore.loadApplications()) {
            if (record.getApplicationId().equals(applicationId)) {
                return record;
            }
        }
        return null;
    }
    
    public void updateStatus(String applicationId, String status) {
        List<ApplicationRecord> list = dataStore.loadApplications();

        boolean found = false;

        for (ApplicationRecord record : list) {
            if (record.getApplicationId().equals(applicationId)) {
                record.setStatus(status);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Application not found!");
            return;
        }

        dataStore.saveApplications(list);
        System.out.println("Application status updated to: " + status);
    }
}
