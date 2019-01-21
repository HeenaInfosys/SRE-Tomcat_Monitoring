
package com.psl.story;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.psl.steps.LoginSteps;


public class LoginStory extends JUnitStory {

	public LoginStory() {
		Embedder embedder = configuredEmbedder();
		embedder.embedderControls().doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true)
				.doVerboseFiltering(true).useStoryTimeoutInSecs(300)
				.doIgnoreFailureInStories(false);
    }
	
	@Override
	public Configuration configuration() {
		URL storyURL = null;
		try {

			// This requires you to start Maven from the project directory
			storyURL = new URL("file://" + System.getProperty("user.dir")
					+ "/src/test/java");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new MostUsefulConfiguration().useStoryLoader(
				new LoadFromRelativeFile(storyURL)).useStoryReporterBuilder(
				new StoryReporterBuilder().withFormats(Format.HTML, Format.CONSOLE, Format.TXT));

		
	}
	
	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), new LoginSteps())
				.createCandidateSteps();
	}
	
}
