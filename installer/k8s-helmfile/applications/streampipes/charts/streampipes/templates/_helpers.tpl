{{/*
Expand the name of the chart.
*/}}
{{- define "streampipes.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "streampipes.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "streampipes.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "streampipes.labels" -}}
helm.sh/chart: {{ include "streampipes.chart" . }}
{{ include "streampipes.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "streampipes.selectorLabels" -}}
app.kubernetes.io/name: {{ include "streampipes.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
{{- define "streampipes.selectorLabels-backend" -}}
app.kubernetes.io/name: {{ include "streampipes.name" . }}-backend
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
{{- define "streampipes.selectorLabels-ui" -}}
app.kubernetes.io/name: {{ include "streampipes.name" . }}-ui
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "streampipes.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "streampipes.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}
