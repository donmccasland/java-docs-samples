/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.containeranalysis;

// [START containeranalysis_occurrences_for_image]
import com.google.cloud.devtools.containeranalysis.v1.ContainerAnalysisClient;
import io.grafeas.v1.GrafeasClient;
import io.grafeas.v1.Occurrence;
import io.grafeas.v1.ProjectName;
import java.io.IOException;
import java.lang.InterruptedException;

public class OccurrencesForImage {
  // Retrieves all the Occurrences associated with a specified image
  // Here, all Occurrences are simply printed and counted
  public static int getOccurrencesForImage(String resourceUrl, String projectId)
      throws IOException, InterruptedException {
    // Use this style of URL when you use Google Container Registry
	// String resourceUrl = "https://gcr.io/my-project/my-repo/my-image@sha256:123";
	// Use this style of URL when you use Google Artifact Registry
	// String resourceUrl = "https://LOCATION-docker.pkg.dev/my-project/my-repo/my-image@sha256:123";
    // String projectId = "my-project-id";
    final String projectName = ProjectName.format(projectId);
    final String filterStr = String.format("resourceUrl=\"%s\"", resourceUrl);

    // Initialize client that will be used to send requests. After completing all of your requests, 
    // call the "close" method on the client to safely clean up any remaining background resources.
    GrafeasClient client = ContainerAnalysisClient.create().getGrafeasClient();
    int i = 0;
    for (Occurrence o : client.listOccurrences(projectName, filterStr).iterateAll()) {
      // Write custom code to process each Occurrence here
      System.out.println(o.getName());
      i = i + 1;
    }
    return i;
  }
}
// [END containeranalysis_occurrences_for_image]
