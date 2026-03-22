package group99.ta.storage;

import group99.ta.model.ApplicantProfile;
import group99.ta.model.ApplicationRecord;
import group99.ta.model.Opportunity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Central storage class.
 * Keeps all text files under /data and hides file IO from service layer.
 */
public class DataStore {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path APPLICANTS_FILE = DATA_DIR.resolve("applicants.txt");
    private static final Path OPPORTUNITIES_FILE = DATA_DIR.resolve("opportunities.txt");
    private static final Path APPLICATIONS_FILE = DATA_DIR.resolve("applications.txt");

    public DataStore() {
        try {
            Files.createDirectories(DATA_DIR);
            createIfMissing(APPLICANTS_FILE);
            createIfMissing(OPPORTUNITIES_FILE);
            createIfMissing(APPLICATIONS_FILE);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialise data directory.", e);
        }
    }

    private void createIfMissing(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public List<ApplicantProfile> loadApplicants() {
        try {
            List<List<String>> rows = TextTable.readRows(APPLICANTS_FILE);
            List<ApplicantProfile> profiles = new ArrayList<>();
            for (List<String> row : rows) {
                profiles.add(ApplicantProfile.fromRow(row));
            }
            return profiles;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load applicants.", e);
        }
    }

    public void saveApplicants(List<ApplicantProfile> profiles) {
        try {
            List<List<String>> rows = new ArrayList<>();
            for (ApplicantProfile profile : profiles) {
                rows.add(profile.toRow());
            }
            TextTable.writeRows(APPLICANTS_FILE, rows);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save applicants.", e);
        }
    }

    public List<Opportunity> loadOpportunities() {
        try {
            List<List<String>> rows = TextTable.readRows(OPPORTUNITIES_FILE);
            List<Opportunity> opportunities = new ArrayList<>();
            for (List<String> row : rows) {
                opportunities.add(Opportunity.fromRow(row));
            }
            return opportunities;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load opportunities.", e);
        }
    }

    public void saveOpportunities(List<Opportunity> opportunities) {
        try {
            List<List<String>> rows = new ArrayList<>();
            for (Opportunity opportunity : opportunities) {
                rows.add(opportunity.toRow());
            }
            TextTable.writeRows(OPPORTUNITIES_FILE, rows);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save opportunities.", e);
        }
    }

    public List<ApplicationRecord> loadApplications() {
        try {
            List<List<String>> rows = TextTable.readRows(APPLICATIONS_FILE);
            List<ApplicationRecord> applications = new ArrayList<>();
            for (List<String> row : rows) {
                applications.add(ApplicationRecord.fromRow(row));
            }
            return applications;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load applications.", e);
        }
    }

    public void saveApplications(List<ApplicationRecord> applications) {
        try {
            List<List<String>> rows = new ArrayList<>();
            for (ApplicationRecord application : applications) {
                rows.add(application.toRow());
            }
            TextTable.writeRows(APPLICATIONS_FILE, rows);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save applications.", e);
        }
    }
}