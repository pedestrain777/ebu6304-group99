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

    // 1. 提交申请
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

}
