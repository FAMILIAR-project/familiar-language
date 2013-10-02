var flare = {
VSPlatform: {
	 ScenarioRecognition: 10000,
	 Clustering: {
			 DistanceRGBAnd3DClustering: 10001 ,
			 FastClustering: 10002 ,
			 KMeansClustering: 10003 ,
	},
	 ImageAcquisition: 10004,
	 FrameToFrameTracking: 10005,
	 TrackingDataFusion: 10006,
	 ClassificationModule: {
		 Merge: {
				 FusionGravityCenter: 10007 ,
				 FusionStandard: 10008 ,
		},
		 Split: {
				 SplitStandard: 10009 ,
				 SplitGravityCenter: 10010 ,
		},
		 ClassificationStandard: {
			 Density: 10011,
		},
		 Model: {
				 ModelType: {
						 OmegaModel: 10012 ,
						 ThreeDModel: 10013 ,
				},
				 Ellipse: 10014 ,
				 Shape: 10015 ,
				 Parallelepiped: 10016 ,
				 GravityCenter: 10017 ,
		},
		 Contour: 10018,
	},
	 ReferenceImageUpdating: 10019,
	 SegmentationModule: {
		 TraversalAlgorithm: {
			 TraversalParameters: {
					 WithWindow: 10020 ,
					 WithMask: 10021 ,
					 Fast: 10022 ,
			},
			 GridStep: {
					 HighGridStep: 10023 ,
					 LowGridStep: 10024 ,
			},
		},
		 KernelFunction: {
			 ColorType: {
					 GreyLevel: 10025 ,
					 SystemColor: 10026 ,
			},
			 ShadowElimination: 10027,
			 ObjectAspect: {
					 Edge: 10028 ,
					 Region: 10029 ,
			},
		},
		 Threshold: {
				 LowThreshold: 10030 ,
				 MidThreshold: 10031 ,
				 HighThreshold: 10032 ,
		},
		 SegmFineTune: 10033,
	},
	 LongTermTracking: 10034,
}

};
