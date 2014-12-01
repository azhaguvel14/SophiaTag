package edu.ntust.csie.se.mdfk.sophiatag.data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Set;
import java.util.HashSet;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MaterialTagger {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private static final MaterialTagger instance = new MaterialTagger();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Map<String, EventInvoker<? extends AttachmentModifiedListener>> invokers;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private final EventDispatcher eventDispatcher;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static MaterialTagger getInstance() {
		return instance;	
	}
	
	/**
	 * hiding default constructor
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private MaterialTagger() {
		this.eventDispatcher = new EventDispatcher();
		initInvokers();
	}
	
	private void initInvokers() {
		this.invokers = new HashMap<String, EventInvoker<? extends AttachmentModifiedListener>>();
		this.invokers.put("attm", new EventInvoker<MaterialTaggedListener>(MaterialTaggedListener.class) {

			@Override
			public void invoke(MaterialTaggedListener listener,	Object... eventParameters) {
				listener.onTag((Tag)eventParameters[0], (Material)eventParameters[1]);
			}
			
		});
		
		this.invokers.put("dtfm", new EventInvoker<MaterialTaggedListener>(MaterialTaggedListener.class) {

			@Override
			public void invoke(MaterialTaggedListener listener,	Object... eventParameters) {
				listener.onDetag((Tag)eventParameters[0], (Material)eventParameters[1]);
			}
			
		});
		
		this.invokers.put("dm", new EventInvoker<MaterialDiscardedListener>(MaterialDiscardedListener.class) {

			@Override
			public void invoke(MaterialDiscardedListener listener,	Object... eventParameters) {
				listener.onDiscarded((Material)eventParameters[0]);
			}
			
		});
		
		this.invokers.put("ctot", new EventInvoker<TagTextChangedListener>(TagTextChangedListener.class) {

			@Override
			public void invoke(TagTextChangedListener listener,	Object... eventParameters) {
				listener.onTextChanged((String)eventParameters[0], (Tag)eventParameters[1]);
			}
			
		});
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void attachTagToMaterial(Tag tag, Material material) {
		tag.attachedTo(material);
		material.attachedTo(tag);
		
		this.eventDispatcher.dispatch(this.invokers.get("attm"), tag, material);
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void detachTagFromMaterial(Tag tag, Material material) {
		tag.detachedFrom(material);	
		material.detachedFrom(tag);
		
		this.eventDispatcher.dispatch(this.invokers.get("dtfm"), tag, material);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void discardMaterial(Material material) {
		Set<Tag> attachedTags = material.discard();
		for (Tag tag : attachedTags) {
			tag.detachedFrom(material);
		}
		
		this.eventDispatcher.dispatch(this.invokers.get("dm"), material);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void changeTextOfTag(String text, Tag tag) {
		tag.setText(text);
		
		this.eventDispatcher.dispatch(this.invokers.get("ctot"), text, tag);
	}
	
	
	public void addMaterialTaggedListener(MaterialTaggedListener listener) {
		this.eventDispatcher.addListener(MaterialTaggedListener.class, listener);
	}
	
	public void removeMaterialTaggedListener(MaterialTaggedListener listener) {
		this.eventDispatcher.removeListener(MaterialTaggedListener.class, listener);
	}
	
	public void addMaterialDiscardedListener(MaterialDiscardedListener listener) {
		this.eventDispatcher.addListener(MaterialTaggedListener.class, listener);
	}
	
	public void removeMaterialDiscardedListener(MaterialDiscardedListener listener) {
		this.eventDispatcher.removeListener(MaterialDiscardedListener.class, listener);
	}
	
	public void addTagTextChangedListener(TagTextChangedListener listener) {
		this.eventDispatcher.addListener(TagTextChangedListener.class, listener);
	}
	
	public void removeTagTextChangedListener(TagTextChangedListener listener) {
		this.eventDispatcher.removeListener(TagTextChangedListener.class, listener);
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	private class EventDispatcher {
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @generated
		 * @ordered
		 */
		
		private Map<Class<? extends AttachmentModifiedListener>, List<AttachmentModifiedListener>> listeners;
		
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @generated
		 */
		public EventDispatcher(){
			this.listeners = new HashMap<Class<? extends AttachmentModifiedListener>, List<AttachmentModifiedListener>>();
		}
	
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @generated
		 * @ordered
		 */
		
		public <T extends AttachmentModifiedListener> void dispatch(EventInvoker<T> invoker, Object... eventParameters) {
			if (!isListenerClassExist(invoker.getListenerClass())) {
				return;
			}
			
			List<AttachmentModifiedListener> listenerList = this.getListenersOfSpecificClass(invoker.getListenerClass());
			for (int i = listenerList.size() - 1; i >= 0; i--) {
				invoker.invoke(invoker.getListenerClass().cast(listenerList.get(i)), eventParameters);
			}
		}
		
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @generated
		 * @ordered
		 */
		
		public void addListener(Class<? extends AttachmentModifiedListener> listenerClass, AttachmentModifiedListener listener) {
			if (!isListenerClassExist(listenerClass)) {
				this.listeners.put(listenerClass, new ArrayList<AttachmentModifiedListener>());
			}
			
			this.getListenersOfSpecificClass(listenerClass).add(listener);
		}
		
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @generated
		 * @ordered
		 */
		
		public void removeListener(Class<? extends AttachmentModifiedListener> listenerClass, AttachmentModifiedListener listener) {
			if (!isListenerClassExist(listenerClass)) {
				return;
			}
			
			this.getListenersOfSpecificClass(listenerClass).remove(listener);
		}
		
		private boolean isListenerClassExist(Class<? extends AttachmentModifiedListener> listenerClass) {
			return this.listeners.containsKey(listenerClass);
		}
		
		private List<AttachmentModifiedListener> getListenersOfSpecificClass(Class<? extends AttachmentModifiedListener> listenerClass) {
			return this.listeners.get(listenerClass);
		}
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	private abstract class EventInvoker<T extends AttachmentModifiedListener>{
		private final Class<T> caster;
		public EventInvoker(Class<T> listenerClass) {
			this.caster = listenerClass;
		}
		
		public Class<T> getListenerClass() {
			return this.caster;
		}
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @return 
		 * @generated
		 * @ordered
		 */
		
		public abstract void invoke(T listener, Object... eventParameters) ;
		
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public  interface AttachmentModifiedListener {
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public  interface TagTextChangedListener extends AttachmentModifiedListener {
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @return 
		 * @generated
		 * @ordered
		 */
		
		public void onTextChanged(String oldText, Tag newTag) ;
		
		
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public  interface MaterialDiscardedListener extends AttachmentModifiedListener	{
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @return 
		 * @generated
		 * @ordered
		 */
		
		public void onDiscarded(Material material) ;
		
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public  interface MaterialTaggedListener extends AttachmentModifiedListener {
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @return 
		 * @generated
		 * @ordered
		 */
		
		public void onDetag(Tag tag, Material material) ;
		
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @return 
		 * @generated
		 * @ordered
		 */
		
		public void onTag(Tag tag, Material material) ;
		
		
	}

	
}

