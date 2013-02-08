VideoSurveillanceSystem : [ScenarioRecognition] Clustering ImageAcquisition FrameToFrameTracking [TrackingDataFusion] ClassificationModule ReferenceImageUpdating SegmentationModule [LongTermTracking] :: _VideoSurveillanceSystem ;

Clustering : DistanceRGBAnd3DClustering
	| FastClustering
	| KMeansClustering ;

ClassificationModule : [Merge] [Split] ClassificationStandard Model :: _ClassificationModule ;

Merge : FusionGravityCenter
	| FusionStandard ;

Split : SplitStandard
	| SplitGravityCenter ;

Model : ModelType
	| Ellipse
	| Shape
	| Parallelepiped
	| GravityCenter ;

ModelType : OmegaModel
	| ThreeDModel ;

SegmentationModule : TraversalAlgorithm KernelFunction :: _SegmentationModule ;

TraversalAlgorithm : TraversalParameters+ GridStep :: _TraversalAlgorithm ;

TraversalParameters : WithWindow
	| WithMask
	| Fast ;

KernelFunction : ColorType [ShadowElimination] ObjectAspect :: _KernelFunction ;

ColorType : GreyLevel
	| SystemColor ;

ObjectAspect : Edge
	| Region ;

