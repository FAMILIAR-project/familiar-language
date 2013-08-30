var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 ObjectOfInterest: {
			 Cardinality: {
					 SingleObject: 10000 ,
					 GroupOfObjects: 10001 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10002,
					},
					 Manufactured: {
						 Vehicle: 10003,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10004 ,
							 SelfPropelled: 10005 ,
					},
					 Immovable: 10006 ,
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
					 LightingVariation: {
							 Flashes: 10016 ,
							 HeadLight: 10017 ,
							 Shadows: 10018 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10019 ,
							 SeaOceanWaves: 10020 ,
							 VegetationMovement: 10021 ,
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
				 Scenery3DModel: 10025,
				 DeploymentArchitecture: {
					 ClientDefined: 10026,
					 Server: 10027,
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10028 ,
								 MultiSensor: 10029 ,
						},
						 Camera: {
							 CameraType: {
									 BlackAndWhite: 10030 ,
									 Color: 10031 ,
									 Infrared: 10032 ,
							},
							 Resolution: 10033,
							 FieldOfView: {
									 Large: 10034 ,
									 Narrow: 10035 ,
							},
							 DepthOfField: 10036,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10037,
									},
									 LowFrameRate: 10038 ,
									 IntermediateFrameRate: 10039 ,
							},
						},
						 SensorView: {
								 SideView: 10040 ,
								 TopView: 10041 ,
								 NormalView: 10042 ,
						},
					},
					 ProgrammerDefined: 10043,
				},
				 BehaviourLibrary: 10044,
			},
		},
	},
	 VSPlatform: {
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10045 ,
				 FastClustering: 10046 ,
				 KMeansClustering: 10047 ,
		},
		 ReferenceImageUpdating: 10048,
		 ImageAcquisition: 10049,
		 SegmentationModule: {
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10050 ,
						 LowGridStep: 10051 ,
				},
				 TraversalParameters: {
						 WithWindow: 10052 ,
						 WithMask: 10053 ,
						 Fast: 10054 ,
				},
			},
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10055 ,
						 Region: 10056 ,
				},
				 ColorType: {
						 GreyLevel: 10057 ,
						 SystemColor: 10058 ,
				},
				 ShadowElimination: 10059,
			},
			 SegmFineTune: 10060,
			 Threshold: {
					 LowThreshold: 10061 ,
					 MidThreshold: 10062 ,
					 HighThreshold: 10063 ,
			},
		},
		 TrackingDataFusion: 10064,
		 LongTermTracking: 10065,
		 ClassificationModule: {
			 ClassificationStandard: {
				 Density: 10066,
			},
			 Contour: 10067,
			 Model: {
					 ModelType: {
							 OmegaModel: 10068 ,
							 ThreeDModel: 10069 ,
					},
					 Ellipse: 10070 ,
					 Shape: 10071 ,
					 Parallelepiped: 10072 ,
					 GravityCenter: 10073 ,
			},
			 Merge: {
					 FusionGravityCenter: 10074 ,
					 FusionStandard: 10075 ,
			},
			 Split: {
					 SplitStandard: 10076 ,
					 SplitGravityCenter: 10077 ,
			},
		},
		 FrameToFrameTracking: 10078,
		 ScenarioRecognition: 10079,
	},
}

};

