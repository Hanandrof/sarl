/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2020 the original authors or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.sarl.lang.core;

import java.lang.reflect.Constructor;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.inject.Inject;

import com.google.common.reflect.TypeToken;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

import io.sarl.lang.SARLVersion;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.util.AtomicClearableReference;
import io.sarl.lang.util.OutParameter;


/**
 * The definition of the notion of Agent in SARL.
 * An agent is an autonomous entity having some intrinsic skills to realize
 * the capacities it exhibits. An agent defines a context.
 *
 * @author $Author: srodriguez$
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification(SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING)
public class Agent extends AgentProtectedAPIObject implements Identifiable {

	private static final DynamicSkillProvider SINGLETON = (it0, it1) -> null;

	private final UUID id;

	private final UUID parentID;

	/** Skill repository.
	 */
	private ConcurrentMap<Class<? extends Capacity>, AtomicClearableReference<Skill>> skillRepository = new ConcurrentHashMap<>();

	private DynamicSkillProvider skillProvider;

	/**
	 * Creates a new agent with a parent <code>parentID</code> and initialize the built-in capacity
	 * with the given provider.
	 *
	 * @param provider the provider of built-in capacities for this agent. If {@code null}, the builtin
	 *     capacities will not be initialized.
	 * @param parentID the agent's parent.
	 * @param agentID the identifier of the agent, or
	 *                  {@code null} for computing it randomly.
	 * @deprecated See {@link #Agent(UUID, UUID, DynamicSkillProvider)}.
	 */
	@Deprecated
	@Inject
	public Agent(
			BuiltinCapacitiesProvider provider,
			UUID parentID,
			UUID agentID) {
		this(parentID, agentID);
		if (provider != null) {
			final Map<Class<? extends Capacity>, Skill> builtinCapacities = provider.getBuiltinCapacities(this);
			if (builtinCapacities != null && !builtinCapacities.isEmpty()) {
				for (final Entry<Class<? extends Capacity>, Skill> bic : builtinCapacities.entrySet()) {
					$mapCapacityGetOld(bic.getKey(), bic.getValue());
				}
			}
		}
	}

	/**
	 * Creates a new agent with a parent <code>parentID</code> without initializing the built-in capacities.
	 *
	 * @param parentID the agent's spawner.
	 * @param agentID the identifier of the agent, or
	 *                  {@code null} for computing it randomly.
	 * @since 0.5
	 */
	public Agent(
			UUID parentID,
			UUID agentID) {
		this(parentID, agentID, null);
	}

	/**
	 * Creates a new agent with a parent <code>parentID</code> without initializing the built-in capacities.
	 *
	 * @param parentID the agent's spawner.
	 * @param agentID the identifier of the agent, or
	 *                  {@code null} for computing it randomly.
	 * @param skillProvider provides the skills dynamically on demand.
	 * @since 0.6
	 */
	@Inject
	public Agent(
			UUID parentID,
			UUID agentID,
			DynamicSkillProvider skillProvider) {
		this.parentID = parentID;
		this.id = (agentID == null) ? UUID.randomUUID() : agentID;
		if (skillProvider == null) {
			this.skillProvider = SINGLETON;
		} else {
			this.skillProvider = skillProvider;
		}
	}

	@Override
	@Pure
	protected void toString(ToStringBuilder builder) {
		builder.add("type", getClass().getSimpleName()); //$NON-NLS-1$
		builder.add("id", this.id); //$NON-NLS-1$
		builder.add("parentID", this.parentID); //$NON-NLS-1$
	}

	/**
	 * Replies the agent's parent's ID.
	 *
	 * @return the identifier of the agent's parent.
	 */
	@Pure
	public UUID getParentID() {
		return this.parentID;
	}

	@Override
	@Pure
	public UUID getID() {
		return this.id;
	}

	/** Change te dynamic skill provider.
	 *
	 * <p>This function is part of the private API of the library.
	 *
	 * @param provider the skill provider.
	 * @since 0.6
	 */
	void $setDynamicSkillProvider(DynamicSkillProvider provider) {
		if (provider == null) {
			this.skillProvider = SINGLETON;
		} else {
			this.skillProvider = provider;
		}
	}

	/** Replies the skill repository.
	 *
	 * <p>This function is part of the private API of the library.
	 *
	 * @return the skill repository.
	 */
	ConcurrentMap<Class<? extends Capacity>, AtomicClearableReference<Skill>> $getSkillRepository() {
		return this.skillRepository;
	}

	/** Create the mapping between the capacity and the skill.
	 *
	 * <p>This function is part of the private API of the library.
	 *
	 * @param capacity the capacity to map.
	 * @param skill the skill to map.
	 * @return the previous mapping, or {@code null}.
	 * @see #$mapCapacityGetNew(Class, Skill)
	 * @see #$mapCapacityGetOldAndNew(Class, Skill)
	 */
	AtomicClearableReference<Skill> $mapCapacityGetOld(Class<? extends Capacity> capacity, Skill skill) {
		return $getSkillRepository().put(capacity, new AtomicClearableReference<>(skill));
	}

	/** Create the mapping between the capacity and the skill.
	 *
	 * <p>This function is part of the private API of the library.
	 *
	 * @param capacity the capacity to map.
	 * @param skill the skill to map.
	 * @return the created reference, never {@code null}.
	 * @see #$mapCapacityGetOld(Class, Skill)
	 * @see #$mapCapacityGetOldAndNew(Class, Skill)
	 */
	AtomicClearableReference<Skill> $mapCapacityGetNew(Class<? extends Capacity> capacity, Skill skill) {
		final AtomicClearableReference<Skill> newReference = new AtomicClearableReference<>(skill);
		$getSkillRepository().put(capacity, newReference);
		return newReference;
	}

	/** Create the mapping between the capacity and the skill.
	 *
	 * <p>This function is part of the private API of the library.
	 *
	 * @param capacity the capacity to map.
	 * @param skill the skill to map.
	 * @return the previous and new mappings, never {@code null}.
	 * @since 16.0
	 * @see #$mapCapacityGetOld(Class, Skill)
	 * @see #$mapCapacityGetNew(Class, Skill)
	 */
	Pair<AtomicClearableReference<Skill>, AtomicClearableReference<Skill>> $mapCapacityGetOldAndNew(Class<? extends Capacity> capacity, Skill skill) {
		final AtomicClearableReference<Skill> newReference = new AtomicClearableReference<>(skill);
		final AtomicClearableReference<Skill> oldReference = $getSkillRepository().put(capacity, newReference);
		return Pair.of(oldReference, newReference);
	}

	/**
	 * Set the skill for the {@link Capacity} <code>capacity</code>.
	 *
	 * @param <S> - type of the skill.
	 * @param capacity capacity to set.
	 * @param skill implementation of <code>capacity</code>.
	 * @return the skill that was set.
	 * @deprecated since 0.4, see {@link #setSkill(Skill, Class...)}
	 */
	@Inline("setSkill($2, $1)")
	@Deprecated
	protected <S extends Skill> S setSkill(Class<? extends Capacity> capacity, S skill) {
		return setSkill(skill, capacity);
	}

	@Override
	@SafeVarargs
	protected final <S extends Skill> S setSkill(S skill, Class<? extends Capacity>... capacities) {
		$setSkill(skill, capacities);
		return skill;
	}

	/** Add a skill to the agent.
	 *
	 * @param skill the new skill.
	 * @param capacities the implemented capacities by the skill.
	 * @return the reference to the skill.
	 * @since 16.0
	 */
	@SafeVarargs
	protected final AtomicClearableReference<Skill> $setSkill(Skill skill, Class<? extends Capacity>... capacities) {
		assert skill != null : "the skill parameter must not be null"; //$NON-NLS-1$
		skill.setOwner(this);
		final OutParameter<AtomicClearableReference<Skill>> newRef = new OutParameter<>();
		if (capacities == null || capacities.length == 0) {
			runOnImplementedCapacities(skill, capacity -> {
				final AtomicClearableReference<Skill> oldS;
				if (newRef.get() == null) {
					final Pair<AtomicClearableReference<Skill>, AtomicClearableReference<Skill>> pair = $mapCapacityGetOldAndNew(capacity, skill);
					newRef.set(pair.getValue());
					oldS = pair.getKey();
				} else {
					oldS = $mapCapacityGetOld(capacity, skill);
				}
				skill.registerUse();
				if (oldS != null) {
					final Skill oldSkill = oldS.clear();
					if (oldSkill != null && oldSkill != skill) {
						oldSkill.unregisterUse();
					}
				}
			});
		} else {
			for (final Class<? extends Capacity> capacity : capacities) {
				assert capacity != null : "the capacity parameter must not be null"; //$NON-NLS-1$
				assert capacity.isInterface() : "the capacity parameter must be an interface"; //$NON-NLS-1$
				if (!capacity.isInstance(skill)) {
					throw new InvalidParameterException(
							"the skill must implement the given capacity " //$NON-NLS-1$
							+ capacity.getName());
				}
				final AtomicClearableReference<Skill> oldS;
				if (newRef.get() == null) {
					final Pair<AtomicClearableReference<Skill>, AtomicClearableReference<Skill>> pair = $mapCapacityGetOldAndNew(capacity, skill);
					newRef.set(pair.getValue());
					oldS = pair.getKey();
				} else {
					oldS = $mapCapacityGetOld(capacity, skill);
				}
				skill.registerUse();
				if (oldS != null) {
					final Skill oldSkill = oldS.clear();
					if (oldSkill != null && oldSkill != skill) {
						oldSkill.unregisterUse();
					}
				}
			}
		}
		return newRef.get();
	}

	private static void runOnImplementedCapacities(Skill skill, Procedure1<? super Class<? extends Capacity>> callback) {
		TypeToken.of(skill.getClass()).getTypes().interfaces().stream().forEach(it -> {
			final Class<?> type = it.getRawType();
			if (Capacity.class.isAssignableFrom(type) && !Capacity.class.equals(type)) {
				callback.apply(type.asSubclass(Capacity.class));
			}
		});
	}

	@Override
	@Inline("setSkill($2, $1)")
	protected <S extends Skill> void operator_mappedTo(Class<? extends Capacity> capacity, S skill) {
		setSkill(skill, capacity);
	}

	@Override
	protected <S extends Capacity> S clearSkill(Class<S> capacity) {
		assert capacity != null;
		final AtomicClearableReference<Skill> reference = $getSkillRepository().remove(capacity);
		if (reference != null) {
			final Skill skill = reference.clear();
			if (skill != null) {
				skill.unregisterUse();
				return capacity.cast(skill);
			}
		}
		return null;
	}

	@Override
	@Pure
	protected final <S extends Capacity> S getSkill(Class<S> capacity) {
		assert capacity != null;
		final AtomicClearableReference<Skill> skill = $getSkill(capacity);
		assert skill != null;
		return $castSkill(capacity, skill);
	}

	/** Cast the skill reference to the given capacity type.
	 *
	 * @param <S> the expected capacity type.
	 * @param capacity the expected capacity type.
	 * @param skillReference the skill reference.
	 * @return the skill casted to the given capacity.
	 */
	@Pure
	protected <S extends Capacity> S $castSkill(Class<S> capacity, AtomicClearableReference<Skill> skillReference) {
		final S skill = capacity.cast(skillReference.get());
		if (skill == null) {
			throw new UnimplementedCapacityException(capacity, getID());
		}
		return skill;
	}

	@Override
	@Pure
	protected AtomicClearableReference<Skill> $getSkill(Class<? extends Capacity> capacity) {
		AtomicClearableReference<Skill> skill = $getSkillRepository().get(capacity);
		// Check if the stored skill is still not empty
		if (skill != null) {
			final Skill s = skill.get();
			if (s == null) {
				skill = null;
			}
		}
		if (skill == null) {
			// Try to load dynamically the skill
			skill = createSkillFromProvider(capacity);
			if (skill == null) {
				// Use the default skill declaration if present.
				skill = createDefaultSkill(capacity);
			}
			if (skill == null) {
				throw new UnimplementedCapacityException(capacity, getID());
			}
		}
		return skill;
	}

	private AtomicClearableReference<Skill> createSkillFromProvider(Class<? extends Capacity> capacity) {
		assert this.skillProvider != null;
		final AtomicClearableReference<Skill> reference = this.skillProvider.installSkill(this, capacity);
		if (reference != null) {
			final Skill s = reference.get();
			if (s != null) {
				return reference;
			}
		}
		return null;
	}

	private AtomicClearableReference<Skill> createDefaultSkill(Class<? extends Capacity> capacity) {
		final DefaultSkill annotation = capacity.getAnnotation(DefaultSkill.class);
		if (annotation != null) {
			try {
				final Class<? extends Skill> type = annotation.value();
				Constructor<? extends Skill> cons;
				try {
					cons = type.getConstructor(Agent.class);
					cons.setAccessible(true);
					final Skill skillInstance = cons.newInstance(this);
					return $setSkill(skillInstance);
				} catch (Throwable exception) {
					cons = type.getConstructor();
				}
				cons.setAccessible(true);
				final Skill skillInstance = cons.newInstance();
				final AtomicClearableReference<Skill> ref = $setSkill(skillInstance);
				if (ref != null) {
					final Skill s = ref.get();
					if (s != null) {
						return ref;
					}
				}
			} catch (Throwable exception) {
				throw new UnimplementedCapacityException(capacity, getID(), exception);
			}
		}
		return null;
	}

	@Override
	@Pure
	protected boolean hasSkill(Class<? extends Capacity> capacity) {
		assert capacity != null;
		if (!$getSkillRepository().containsKey(capacity)) {
			if (this.skillProvider != null) {
				final AtomicClearableReference<Skill> reference = this.skillProvider.installSkill(this, capacity);
				if (reference != null) {
					return true;
				}
			}
			final DefaultSkill annotation = capacity.getAnnotation(DefaultSkill.class);
			return annotation != null && annotation.value() != null;
		}
		return true;
	}

	@Override
	@Pure
	@Inline(value = "($1 != null && $0getID().equals($1.getUUID()))", constantExpression = true)
	protected boolean isMe(Address address) {
		return address != null && isMe(address.getUUID());
	}

	@Override
	@Pure
	@Inline(value = "getID().equals($1)")
	protected boolean isMe(UUID uID) {
		return uID != null && getID().equals(uID);
	}

	@Override
	@Pure
	@Inline(value = "($1 != null && $0getID().equals($1.getSource().getUUID()))", constantExpression = true)
	protected boolean isFromMe(Event event) {
		return event != null && isMe(event.getSource());
	}

}
