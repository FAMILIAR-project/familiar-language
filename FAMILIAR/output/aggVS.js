var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 ObjectOfInterest: {
			 Sort: {
					 Natural: {
						 Person: 10000,
					},
					 Manufactured: {
						 Vehicle: 10001,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10002 ,
							 SelfPropelled: 10003 ,
					},
					 Immovable: 10004 ,
			},
			 Cardinality: {
					 SingleObject: 10005 ,
					 GroupOfObjects: 10006 ,
			},
		},
		 QualityOfService: {
				 ComputerLoad: 10007 ,
				 ResponseTime: 10008 ,
				 Quality: {
						 Sensitivity: 10009 ,
						 Precision: {
							 LessPrecision: 10010,
						},
				},
		},
		 Task: {
				 Counting: 10011 ,
				 Tracking: 10012 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10013,
				},
		},
		 Scene: {
			 Environment: {
				 LightingType: {
						 ArtificialLight: 10014 ,
						 NaturalLight: 10015 ,
				},
				 Noise: {
					 BackgroundMovement: {
							 SandOrDust: 10016 ,
							 SeaOceanWaves: 10017 ,
							 VegetationMovement: 10018 ,
					},
					 LightingVariation: {
							 Flashes: 10019 ,
							 HeadLight: 10020 ,
							 Shadows: 10021 ,
					},
				},
				 LightingConditions: {
						 Indoors: 10022 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10023 ,
									 Night: 10024 ,
							},
						},
				},
			},
			 APrioriKnowledge: {
				 BehaviourLibrary: 10025,
				 DeploymentArchitecture: {
					 ClientDefined: 10026,
					 ProgrammerDefined: 10027,
					 Server: 10028,
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10029 ,
								 MultiSensor: 10030 ,
						},
						 SensorView: {
								 SideView: 10031 ,
								 TopView: 10032 ,
								 NormalView: 10033 ,
						},
						 Camera: {
							 Resolution: 10034,
							 CameraType: {
									 BlackAndWhite: 10035 ,
									 Color: 10036 ,
									 Infrared: 10037 ,
							},
							 DepthOfField: 10038,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10039,
									},
									 LowFrameRate: 10040 ,
									 IntermediateFrameRate: 10041 ,
							},
							 FieldOfView: {
									 Large: 10042 ,
									 Narrow: 10043 ,
							},
						},
					},
				},
				 Scenery3DModel: 10044,
			},
		},
	},
	 VSPlatform: {
		 LongTermTracking: 10045,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10046 ,
				 FastClustering: 10047 ,
				 KMeansClustering: 10048 ,
		},
		 ImageAcquisition: 10049,
		 SegmentationModule: {
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10050 ,
						 Region: 10051 ,
				},
				 ShadowElimination: 10052,
				 ColorType: {
						 GreyLevel: 10053 ,
						 SystemColor: 10054 ,
				},
			},
			 TraversalAlgorithm: {
				 TraversalParameters: {
						 WithWindow: 10055 ,
						 WithMask: 10056 ,
						 Fast: 10057 ,
				},
				 GridStep: {
						 HighGridStep: 10058 ,
						 LowGridStep: 10059 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10060 ,
					 MidThreshold: 10061 ,
					 HighThreshold: 10062 ,
			},
			 SegmFineTune: 10063,
		},
		 ClassificationModule: {
			 Model: {
					 ModelType: {
							 OmegaModel: 10064 ,
							 ThreeDModel: 10065 ,
					},
					 Ellipse: 10066 ,
					 Shape: 10067 ,
					 Parallelepiped: 10068 ,
					 GravityCenter: 10069 ,
			},
			 Contour: 10070,
			 Merge: {
					 FusionGravityCenter: 10071 ,
					 FusionStandard: 10072 ,
			},
			 ClassificationStandard: {
				 Density: 10073,
			},
			 Split: {
					 SplitStandard: 10074 ,
					 SplitGravityCenter: 10075 ,
			},
		},
		 ReferenceImageUpdating: 10076,
		 ScenarioRecognition: 10077,
		 TrackingDataFusion: 10078,
		 FrameToFrameTracking: 10079,
	},
}

};
