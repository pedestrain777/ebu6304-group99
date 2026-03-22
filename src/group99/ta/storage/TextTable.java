package group99.ta.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple tab-separated text storage helper.
 * Uses escaping so that tabs and line breaks can still be stored safely.
 */
public class TextTable {
    private static final String SEPARATOR = "\t";

    private TextTable() {
    }

    public static List<List<String>> readRows(Path path) throws IOException {
        List<List<String>> rows = new ArrayList<>();

        if (!Files.exists(path)) {
            return rows;
        }

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(SEPARATOR, -1);
            List<String> row = new ArrayList<>();
            for (String part : parts) {
                row.add(unescape(part));
            }
            rows.add(row);
        }
        return rows;
    }

    public static void writeRows(Path path, List<List<String>> rows) throws IOException {
        List<String> lines = new ArrayList<>();
        for (List<String> row : rows) {
            List<String> escaped = new ArrayList<>();
            for (String value : row) {
                escaped.add(escape(value));
            }
            lines.add(String.join(SEPARATOR, escaped));
        }
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    private static String escape(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("\\", "\\\\")
                .replace("\t", "\\t")
                .replace("\n", "\\n")
                .replace("\r", "");
    }

    private static String unescape(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("\\t", "\t")
                .replace("\\n", "\n")
                .replace("\\\\", "\\");
    }
}