/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2015  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 * 
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.ui.statistics.core.composite.lazyimplementations;

import static de.ovgu.featureide.fm.core.localization.StringTable.CALCULATING;
import static de.ovgu.featureide.fm.core.localization.StringTable.MORE_THAN;

import org.eclipse.core.runtime.jobs.Job;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.ui.statistics.core.composite.LazyParent;
import de.ovgu.featureide.ui.statistics.core.composite.Parent;
import de.ovgu.featureide.ui.statistics.ui.helper.JobDoneListener;
import de.ovgu.featureide.ui.statistics.ui.helper.TreeClickListener;
import de.ovgu.featureide.ui.statistics.ui.helper.jobs.StoppableTreeJob;

/**
 * Parent for the actual {@link ConfigNode}s.
 * 
 * @author Dominik Hamann
 * @author Patrick Haese
 */
public class ConfigParentNode extends LazyParent {
	
	private final FeatureModel model;

	public static class ConfigNode extends Parent {
		private FeatureModel innerModel;

		public ConfigNode(String description, FeatureModel innerModel) {
			super(description, "(double-click to calculate)");
			this.innerModel = innerModel;
		}

		/**
		 * calculates the number of configurations/variants depending on
		 * ignoreAbstract. This method should be called by
		 * {@link TreeClickListener}.
		 * 
		 * @param timeout
		 *            defines how long the SAT-Solver may take to accomplish the
		 *            task.
		 * @param priority
		 *            for the job.
		 */
		public void calculate(final long timeout, final int priority) {
			Job job = new StoppableTreeJob(CALCULATING + this.description, this) {
				private String calculateConfigs() {
					boolean ignoreAbstract = description.equals(DESC_CONFIGS);
					if (!ignoreAbstract && innerModel.getAnalyser().countConcreteFeatures() == 0) {
						// case: there is no concrete feature so there is only one program variant,
						// without this the calculation least much to long
						return "1";
					}

					final long number = new Configuration(innerModel, false, ignoreAbstract).number(timeout);
					
					return ((number < 0) ? MORE_THAN + (-number - 1) : String.valueOf(number));
				}

				@Override
				protected boolean work() throws Exception {
					setValue(calculateConfigs());
					return true;
				}
			};
			job.setPriority(priority);
			JobDoneListener listener = JobDoneListener.getInstance();
			if (listener != null) {
				job.addJobChangeListener(listener);
			}
			job.schedule();
		}
	}

	public ConfigParentNode(String description, FeatureModel model) {
		super(description);
		this.model = model;
	}

	@Override
	protected void initChildren() {
		addChild(new ConfigNode(DESC_CONFIGS, model));
		addChild(new ConfigNode(DESC_VARIANTS, model));
	}
}
